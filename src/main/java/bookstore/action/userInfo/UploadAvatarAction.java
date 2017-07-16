package bookstore.action.userInfo;

import bookstore.action.BaseAction;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.AppService;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.*;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public class UploadAvatarAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private File file;
    private String fileContentType;
    private String fileFileName;
    private String savePath;
    private String title;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    private AppService appService;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return Long.toString(System.currentTimeMillis()) + "_" + fileFileName ;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getSavePath() {
        return ServletActionContext.getServletContext().getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String execute() throws Exception {
        InputStream is = new FileInputStream(getFile());
        String fileName = getFileFileName();
        OutputStream os = new FileOutputStream(getSavePath() + "/" + fileName);
        IOUtils.copy(is, os);
        os.flush();
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = appService.getUserByUsername(username);
        UserInfo userInfo = appService.getUserInfoByUid(user.getUid());
        userInfo.setAvatar("/image/" + fileName);
        appService.updateUserInfo(userInfo);
        return SUCCESS;
    }
}

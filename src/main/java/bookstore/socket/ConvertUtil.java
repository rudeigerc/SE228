package bookstore.socket;

import bookstore.dao.UserDao;
import bookstore.dao.UserInfoDao;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ConvertUtil {

    private UserDao userDao;
    private UserInfoDao userInfoDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public String formMessage(String content) {
        try {
            JsonObject contentObj = new JsonParser().parse(content).getAsJsonObject();
            int uid = contentObj.get("uid").getAsInt();
            String datetime = contentObj.get("datetime").getAsString();
            SimpleDateFormat insdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            SimpleDateFormat outsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String parsedDatetime = outsdf.format(insdf.parse(datetime));
            String chatContent = contentObj.get("content").getAsString();
            String username = userDao.getUserByUid(uid).getUsername();
            String avatar = userInfoDao.getUserInfoByUid(uid).getAvatar();

            JsonObject messageJson = new JsonObject();
            messageJson.addProperty("uid", uid);
            messageJson.addProperty("username", username);
            messageJson.addProperty("datetime", parsedDatetime);
            messageJson.addProperty("avatar", avatar);
            messageJson.addProperty("content", chatContent);
            return messageJson.toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return "error";
        }
    }

}

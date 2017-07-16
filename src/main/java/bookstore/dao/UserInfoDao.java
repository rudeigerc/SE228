package bookstore.dao;

import bookstore.model.UserInfo;
import java.util.List;

/**
 * Created by rudeigerc on 2017/7/17.
 */
public interface UserInfoDao {
    void addUserInfo(UserInfo userInfo);
    void deleteUserInfo(UserInfo userInfo);
    void updateUserInfo(UserInfo userInfo);
    UserInfo getUserInfoByUid(int uid);
    List<UserInfo> getAllUserInfos();
}

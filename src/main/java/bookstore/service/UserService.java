package bookstore.service;

import bookstore.model.User;
import bookstore.model.UserInfo;

import java.util.List;

public interface UserService {

    Integer addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserByUid(int uid);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    List<String> getAllUsernames();

    void addUserInfo(UserInfo userInfo);

    void deleteUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfoByUid(int uid);

    List<UserInfo> getAllUserInfos();
}

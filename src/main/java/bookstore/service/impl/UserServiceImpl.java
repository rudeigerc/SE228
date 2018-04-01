package bookstore.service.impl;

import bookstore.dao.UserDao;
import bookstore.dao.UserInfoDao;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private UserInfoDao userInfoDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public Integer addUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<String> getAllUsernames() {
        return userDao.getAllUsernames();
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoDao.addUserInfo(userInfo);
    }

    @Override
    public void deleteUserInfo(UserInfo userInfo) {
        userInfoDao.deleteUserInfo(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfoByUid(int uid) {
        return userInfoDao.getUserInfoByUid(uid);
    }

    @Override
    public List<UserInfo> getAllUserInfos() {
        return userInfoDao.getAllUserInfos();
    }
}

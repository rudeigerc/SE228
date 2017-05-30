package bookstore.dao;
import bookstore.model.User;
import java.util.List;

/**
 * Created by rudeigerc on 2017/5/22.
 */
public interface UserDao{
    void save(User user);
    void delete(User user);
    void update(User user);
    User getUserByUid(int uid);
    List<User> getAllUsers();
    List<String> getAllUsernames();
}

package bookstore.service;
import java.util.List;
import bookstore.model.User;
/**
 * Created by rudeigerc on 2017/5/22.
 */
public interface AppService {

    Integer addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserByUid(int uid);

    List<User> getAllUsers();
}

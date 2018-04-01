package bookstore.action;

import bookstore.model.Book;
import bookstore.model.User;
import bookstore.model.UserInfo;
import bookstore.permission.AdultPermission;
import bookstore.service.BookService;
import bookstore.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by rudeigerc on 2017/5/27.
 */
public class IndexAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String category;

    private String keyword;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private UserService userService;

    private BookService bookService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute() throws Exception {
        System.setProperty("java.security.policy", "/Users/rudeigerc/Developer/bookstore/src/main/java/bookstore/permission/Permission.policy");
        System.setSecurityManager(new SecurityManager());
        AdultPermission permission = new AdultPermission(category, "list");
        SecurityManager manager = System.getSecurityManager();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByUsername(username);
        UserInfo info = userService.getUserInfoByUid(user.getUid());
        if (info.getAge() < 18) {
            try {
                if (manager != null) manager.checkPermission(permission);
            } catch (SecurityException e) {
                return ERROR;
            }
        }

        List<Book> books;
        if (category != null && keyword == null) {
            books = bookService.getBookByCategory(category);
        }
        else if (keyword != null && category == null) {
            books = bookService.getBookByKeyword(keyword);
        }
        else {
            books = bookService.getAllBooks();
        }
        List<String> categories = bookService.getAllCatagories();
        request().setAttribute("books", books);
        request().setAttribute("categories", categories);
        return SUCCESS;
    }


}

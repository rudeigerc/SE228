package bookstore.auth;

import java.security.Principal;

public class AdminPrincipal implements Principal {

    private String key;
    private String value;

    public AdminPrincipal(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getName() {
        return null;
    }
}

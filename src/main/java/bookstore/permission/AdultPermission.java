package bookstore.permission;

import java.security.Permission;
import java.util.Objects;

public class AdultPermission extends Permission {

    private String action;

    /**
     * Constructs a permission with the specified name.
     *
     * @param name name of the Permission object being created.
     */
    public AdultPermission(String name, String action) {
        super(name);
        this.action = action;
    }

    public boolean implies(Permission permission) {
        if (!(permission instanceof AdultPermission)) return false;
        AdultPermission other = (AdultPermission) permission;
        switch (this.action) {
            case "list":
                return other.action.equals("list") && getName().contains(other.getName());
            case "restrict":
                return other.getName() == null || !getName().contains(other.getName());
            default:
                return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!getClass().equals(obj.getClass())) return false;
        AdultPermission other = (AdultPermission) obj;
        if (!Objects.equals(action, other.action)) return false;
        if ("list".equals(action)) return Objects.equals(getName(), other.getName());
        return false;
    }

    public int hashCode() {
        return Objects.hash(getName(), action);
    }

    public String getActions() {
        return action;
    }
}

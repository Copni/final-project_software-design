package userroles;

import java.util.List;

public interface Role {
    String getName();

    List<String> getPermissions();
}

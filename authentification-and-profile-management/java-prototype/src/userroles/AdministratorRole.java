package userroles;

import java.util.Arrays;
import java.util.List;

public class AdministratorRole implements Role {
    @Override
    public String getName() {
        return "ADMINISTRATOR";
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("MANAGE_USERS", "CONFIGURE_SYSTEM");
    }
}

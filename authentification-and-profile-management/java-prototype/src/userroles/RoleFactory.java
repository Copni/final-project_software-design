package userroles;

import java.util.Map;

public class RoleFactory {
    public Role createRole(String roleType, Map<String, String> roleData) {
        // Factory Method: role creation can evolve without changing services.
        if ("PATIENT".equalsIgnoreCase(roleType)) {
            return new PatientRole(roleData.getOrDefault("insuranceNumber", "UNKNOWN"));
        }
        if ("DOCTOR".equalsIgnoreCase(roleType)) {
            return new DoctorRole(roleData);
        }
        if ("ADMINISTRATOR".equalsIgnoreCase(roleType)) {
            return new AdministratorRole();
        }
        throw new IllegalArgumentException("Unsupported role type: " + roleType);
    }
}

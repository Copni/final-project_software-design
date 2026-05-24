package userroles;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DoctorRole implements Role {
    private final String licenseNumber;
    private final String specialty;

    public DoctorRole(String licenseNumber, String specialty) {
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
    }

    public DoctorRole(Map<String, String> data) {
        this(data.getOrDefault("licenseNumber", "UNKNOWN"), data.getOrDefault("specialty", "General"));
    }

    @Override
    public String getName() {
        return "DOCTOR";
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("MANAGE_AGENDA", "VIEW_PATIENT_APPOINTMENTS");
    }
}

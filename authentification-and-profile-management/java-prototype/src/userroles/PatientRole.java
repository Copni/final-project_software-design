package userroles;

import java.util.Arrays;
import java.util.List;

public class PatientRole implements Role {
    private final String insuranceNumber;

    public PatientRole(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    @Override
    public String getName() {
        return "PATIENT";
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("BOOK_APPOINTMENT", "VIEW_OWN_APPOINTMENTS");
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }
}

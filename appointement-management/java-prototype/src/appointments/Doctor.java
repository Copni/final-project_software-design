package appointments;

public class Doctor {
    private final int id;
    private final String fullName;
    private final String specialty;

    public Doctor(int id, String fullName, String specialty) {
        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialty() {
        return specialty;
    }
}

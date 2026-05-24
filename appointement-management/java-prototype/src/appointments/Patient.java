package appointments;

public class Patient {
    private final int id;
    private final String fullName;

    public Patient(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}

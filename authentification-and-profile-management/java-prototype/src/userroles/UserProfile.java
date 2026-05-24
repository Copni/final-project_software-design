package userroles;

public class UserProfile {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public UserProfile(String firstName, String lastName, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public void updatePersonalInformation(String firstName, String lastName, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

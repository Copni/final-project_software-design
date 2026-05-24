package userroles;

public class SimplePasswordHasher implements PasswordHasher {
    @Override
    public String hash(String password) {
        return "demo-hash:" + password;
    }

    @Override
    public boolean verify(String password, String hash) {
        return hash(password).equals(hash);
    }
}

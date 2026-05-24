package userroles;

import java.util.Date;

public class Session {
    private final String token;
    private final Date startDate;
    private final Date expirationDate;
    private boolean active;

    public Session(String token, Date startDate, Date expirationDate) {
        this.token = token;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.active = true;
    }

    public boolean isValid() {
        return active && new Date().before(expirationDate);
    }

    public void invalidate() {
        active = false;
    }

    public String getToken() {
        return token;
    }

    public Date getStartDate() {
        return startDate;
    }
}

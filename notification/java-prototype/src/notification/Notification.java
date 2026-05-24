package notification;

import java.util.Date;

public class Notification {
    private final int id;
    private final String title;
    private final String message;
    private final String type;
    private final Date createdAt;
    private boolean sent;

    public Notification(int id, String title, String message, String type) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.type = type;
        this.createdAt = new Date();
    }

    public void markAsSent() {
        sent = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isSent() {
        return sent;
    }
}

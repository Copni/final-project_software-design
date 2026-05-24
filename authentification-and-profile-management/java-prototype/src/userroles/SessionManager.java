package userroles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SessionManager {
    private final Map<String, Session> sessions = new HashMap<>();

    public Session createSession(User user) {
        Date now = new Date();
        Date expires = new Date(now.getTime() + 3_600_000);
        Session session = new Session(UUID.randomUUID().toString(), now, expires);
        sessions.put(session.getToken(), session);
        return session;
    }

    public void invalidateSession(Session session) {
        session.invalidate();
    }

    public Optional<Session> validateSession(String token) {
        Session session = sessions.get(token);
        if (session != null && session.isValid()) {
            return Optional.of(session);
        }
        return Optional.empty();
    }
}

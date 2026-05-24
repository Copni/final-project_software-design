# Notification Java Prototype

This prototype illustrates the notification subsystem from the UML class diagram.

## What it demonstrates

- **Observer Pattern**: `Appointment` notifies `AppointmentObserver` implementations when its state changes.
- **Factory Pattern**: `NotificationFactory` creates notification objects for appointment events.
- **SOLID SRP**: `Appointment` manages appointment changes, observers react to events, and `NotificationService` sends notifications.
- **SOLID OCP**: a new notification channel can be added by creating another `AppointmentObserver`.
- **GRASP Low Coupling**: `Appointment` depends on the `AppointmentObserver` interface, not concrete notification channels.

## Run

From this folder:

```bash
javac -d out src/notification/*.java
java -cp out notification.Demo
```

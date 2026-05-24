# Appointment Management Java Prototype

This prototype illustrates the medical appointment management subsystem from the UML class diagram.

## What it demonstrates

- **GRASP Controller**: `AppointmentService` orchestrates scheduling, confirmation, cancellation, completion, and rescheduling use cases.
- **State Pattern**: `AppointmentState` implementations control valid lifecycle transitions.
- **Strategy Pattern**: `AvailabilityStrategy` and `SchedulingStrategy` isolate variable business rules.
- **SOLID SRP**: `AppointmentService` orchestrates use cases, while `Appointment` owns its lifecycle state.
- **SOLID OCP**: new scheduling or availability rules can be added without modifying `AppointmentService`.
- **GRASP Information Expert**: `Appointment` manages its own state transitions.
- **GRASP Protected Variations**: changing rules are hidden behind strategy interfaces.

## Run

From this folder:

```bash
javac -d out src/appointments/*.java
java -cp out appointments.Demo
```

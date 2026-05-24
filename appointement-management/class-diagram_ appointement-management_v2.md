# Applying SOLID/GRASP principles + Strategy and State Pattern:
The design separates responsibilities by moving appointment orchestration from Patient/Doctor to AppointmentService, applying SRP, DIP, and GRASP Controller.
Appointment keeps its own lifecycle behavior, following GRASP Information Expert and High Cohesion.
The State Pattern replaces string-based status handling with state objects to manage valid transitions.
The Strategy Pattern externalizes availability and scheduling rules, supporting OCP and Protected Variations.
This reduces coupling between domain entities and business rules, applying GRASP Low Coupling.
```mermaid
classDiagram
direction LR

class Patient {
  -int id
  -String fullName
}

class Doctor {
  -int id
  -String fullName
  -String specialty
}

class Appointment {
  -int id
  -Date appointmentDate
  -String reason
  -double price
  -AppointmentState state
  +confirm()
  +cancel()
  +complete()
  +reschedule(slot)
}

class TimeSlot {
  -Date date
  -String startTime
  -String endTime
  +overlaps(otherSlot)
}

class AppointmentService {
  -AvailabilityStrategy availabilityStrategy
  -SchedulingStrategy schedulingStrategy
  +scheduleAppointment(patient, doctor, slot, reason)
  +rescheduleAppointment(appointment, newSlot)
  +cancelAppointment(appointment)
  +confirmAppointment(appointment)
  +completeAppointment(appointment)
}

class AppointmentState {
  <<interface>>
  +confirm(appointment)
  +cancel(appointment)
  +complete(appointment)
}

class ScheduledState
class ConfirmedState
class CancelledState
class CompletedState

class AvailabilityStrategy {
  <<interface>>
  +isAvailable(doctor, slot, appointments)
}

class NoOverlapAvailabilityStrategy

class SchedulingStrategy {
  <<interface>>
  +canSchedule(patient, doctor, slot)
}

class StandardSchedulingStrategy
class EmergencySchedulingStrategy

Patient "1" --> "0..*" Appointment : books
Doctor "1" --> "0..*" Appointment : assigned to
Appointment --> TimeSlot : has
Appointment --> AppointmentState : uses

AppointmentState <|.. ScheduledState
AppointmentState <|.. ConfirmedState
AppointmentState <|.. CancelledState
AppointmentState <|.. CompletedState

AppointmentService --> Appointment : manages
AppointmentService --> AvailabilityStrategy : uses
AppointmentService --> SchedulingStrategy : uses

AvailabilityStrategy <|.. NoOverlapAvailabilityStrategy
SchedulingStrategy <|.. StandardSchedulingStrategy
SchedulingStrategy <|.. EmergencySchedulingStrategy
```
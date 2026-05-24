# Applying SOLID/GRASP Principles + Strategy and State Patterns:
The design applies SRP by moving agenda-related behavior out of Doctor and keeping each class focused on one responsibility.
Using GRASP Expert, availability checks are handled by Agenda, WorkingHour, and UnavailablePeriod, since they own the required information.
AgendaService acts as a GRASP Controller, coordinating use cases without holding all business rules.
The Factory Pattern centralizes appointment creation, supporting OCP when adding new appointment types.
The State Pattern moves appointment status behavior into state classes, improving cohesion and reducing conditional logic
```mermaid
classDiagram
direction LR

class Doctor {
  -int id
  -String fullName
  -String specialty
}

class Agenda {
  -int id
  -List~WorkingHour~ workingHours
  -List~UnavailablePeriod~ unavailablePeriods
  -List~Appointment~ appointments
  +addWorkingHours(hours)
  +addUnavailablePeriod(period)
  +addAppointment(appointment)
  +isAvailable(slot)
  +getAppointmentsForDay(date)
}

class WorkingHour {
  -DayOfWeek dayOfWeek
  -Time startTime
  -Time endTime
  +contains(slot)
}

class UnavailablePeriod {
  -DateTime startDate
  -DateTime endDate
  -String reason
  +overlaps(slot)
}

class TimeSlot {
  -DateTime start
  -DateTime end
  +overlaps(otherSlot)
}

class Appointment {
  -int id
  -TimeSlot slot
  -AppointmentState state
  +confirm()
  +cancel()
  +complete()
  +reschedule(newSlot)
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

class AppointmentFactory {
  +createConsultation(slot)
  +createEmergency(slot)
  +createFollowUp(slot)
}

class AgendaService {
  -AppointmentFactory appointmentFactory
  +scheduleConsultation(doctor, slot)
  +scheduleEmergency(doctor, slot)
  +cancelAppointment(appointment)
  +confirmAppointment(appointment)
}

Doctor "1" --> "1" Agenda : owns
Agenda "1" --> "*" WorkingHour : contains
Agenda "1" --> "*" UnavailablePeriod : contains
Agenda "1" --> "*" Appointment : contains
Appointment --> TimeSlot : has
Appointment --> AppointmentState : delegates behavior to
AppointmentState <|.. ScheduledState
AppointmentState <|.. ConfirmedState
AppointmentState <|.. CancelledState
AppointmentState <|.. CompletedState
AgendaService --> Agenda : coordinates
AgendaService --> AppointmentFactory : creates appointments
AppointmentFactory --> Appointment : instantiates
```
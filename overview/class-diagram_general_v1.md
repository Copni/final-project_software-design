# Diagrammes de classes Mermaid minimalistes — version AVANT SOLID/GRASP

Ces diagrammes représentent une version volontairement plus simple et plus directe du système **HealthCare Appointment Management System**, construite à partir des use-cases originaux.

Objectif : fournir une base “avant refactoring” pour comparer avec une version améliorée appliquant SOLID, GRASP et des design patterns.

Cette version reste cohérente fonctionnellement, mais elle ne cherche pas à optimiser l’architecture. Les responsabilités sont parfois regroupées dans des classes de gestion générales, les dépendances sont plus directes, et aucun pattern comme Strategy, Factory, Observer ou Singleton n’est introduit.

---

## 1. Package : Authentification and Profile Management System

```mermaid
classDiagram
direction LR

class User {
  -int id
  -String firstName
  -String lastName
  -String email
  -String phone
  -String password
  -String role
  -String status
  -String address
  -boolean emailNotifications
  -boolean smsNotifications
  -boolean inAppNotifications
  +register()
  +login(email, password)
  +logout()
  +resetPassword()
  +updatePersonalInformation()
  +changePassword()
  +manageNotificationPreferences()
}

class Patient {
  -String insuranceNumber
  +browseServices()
  +scheduleAppointment()
}

class Doctor {
  -String licenseNumber
  -String specialty
  +manageAgenda()
}

class Administrator {
  +manageUsers()
  +assignRoles()
}

class AuthManager {
  -List~User~ users
  +registerAccount(user)
  +verifyIdentity(user)
  +login(email, password)
  +logout(user)
  +resetPassword(email)
  +updateProfile(user)
}

class Session {
  -String token
  -Date startDate
  -Date expirationDate
  -boolean active
  +isValid()
}

User <|-- Patient
User <|-- Doctor
User <|-- Administrator
AuthManager --> User : manages
AuthManager --> Session : creates
User --> Session : owns
```

---

## 2. Package : Patient Service Viewer System

```mermaid
classDiagram
direction LR

class Patient {
  -int id
  -String fullName
  +browseClinics()
  +browseHealthcareServices()
  +searchServices(keyword)
  +filterServices(criteria)
  +viewClinicDetails(clinic)
  +viewServiceDetails(service)
  +viewDoctorProfile(doctor)
}

class Clinic {
  -int id
  -String name
  -String address
  -String city
  -String phone
  -List~HealthcareService~ services
  +getDetails()
  +addService(service)
}

class HealthcareService {
  -int id
  -String name
  -String description
  -String specialty
  -int durationMinutes
  -double fee
  -List~Doctor~ doctors
  +getDetails()
  +isAvailable()
}

class Doctor {
  -int id
  -String fullName
  -String specialty
  -String clinicName
  +getProfile()
  +getAvailableSlots()
}

class ServiceCatalog {
  -List~Clinic~ clinics
  -List~HealthcareService~ services
  +browseClinics()
  +browseServices()
  +searchServices(keyword)
  +filterBySpecialty(specialty)
  +filterByLocation(location)
  +filterByAvailability(date)
}

Patient --> ServiceCatalog : uses
ServiceCatalog --> Clinic : contains
Clinic --> HealthcareService : offers
HealthcareService --> Doctor : assigned doctors
Patient --> Clinic : views
Patient --> HealthcareService : views
Patient --> Doctor : views
```

---

## 3. Package : Appointment Management System

```mermaid
classDiagram
direction LR

class Patient {
  -int id
  -String fullName
  +scheduleAppointment()
  +modifyAppointment()
  +cancelAppointment()
  +viewAppointments()
  +viewAppointmentHistory()
}

class Doctor {
  -int id
  -String fullName
  -String specialty
  +viewAppointments()
  +markAppointmentAsCompleted()
}

class Appointment {
  -int id
  -Date appointmentDate
  -String startTime
  -String endTime
  -String status
  -String reason
  -double price
  +schedule()
  +modify(newDate, newTime)
  +cancel()
  +confirm()
  +complete()
}

class AppointmentManager {
  -List~Appointment~ appointments
  +scheduleAppointment(patient, doctor, slot)
  +selectAppointmentSlot(doctor, date)
  +checkDoctorAvailability(doctor, slot)
  +validateSchedulingConstraints(appointment)
  +preventOverlappingBookings(doctor, slot)
  +confirmAppointment(appointment)
  +modifyAppointment(appointment)
  +cancelAppointment(appointment)
  +viewAppointments(user)
  +viewAppointmentHistory(patient)
  +markAppointmentAsCompleted(appointment)
  +generateNotification(appointment)
}

class AppointmentHistory {
  -List~Appointment~ pastAppointments
  +addAppointment(appointment)
  +getCompletedAppointments()
  +getCancelledAppointments()
}

class TimeSlot {
  -Date date
  -String startTime
  -String endTime
  -boolean available
  +reserve()
  +release()
}

Patient --> AppointmentManager : uses
Doctor --> AppointmentManager : uses
AppointmentManager --> Appointment : manages
AppointmentManager --> TimeSlot : checks
AppointmentManager --> AppointmentHistory : updates
Patient --> Appointment : owns
Doctor --> Appointment : assigned to
Appointment --> TimeSlot : uses
```

---

## 4. Package : Doctor Agenda Management System

```mermaid
classDiagram
direction LR

class Doctor {
  -int id
  -String fullName
  -String specialty
  +defineWorkingHours()
  +updateAvailability()
  +blockUnavailablePeriods()
  +viewAvailabilityCalendar()
  +viewDailyAgenda()
  +viewWeeklyAgenda()
}

class Agenda {
  -int id
  -List~WorkingHour~ workingHours
  -List~UnavailablePeriod~ unavailablePeriods
  -List~Appointment~ appointments
  +defineWorkingHours(hours)
  +updateAvailability()
  +blockPeriod(period)
  +viewCalendar()
  +viewDailyAgenda(date)
  +viewWeeklyAgenda(week)
  +viewScheduledAppointments()
}

class WorkingHour {
  -String dayOfWeek
  -String startTime
  -String endTime
  +update(startTime, endTime)
}

class UnavailablePeriod {
  -Date startDate
  -Date endDate
  -String reason
  +block()
  +remove()
}

class Appointment {
  -int id
  -Date date
  -String startTime
  -String endTime
  -String status
}

class AgendaManager {
  +defineWorkingHours(doctor, hours)
  +updateAvailability(doctor, agenda)
  +blockUnavailablePeriods(doctor, period)
  +validateAvailabilityConsistency(agenda)
  +preventInvalidScheduleUpdates(agenda)
  +notifyPatientsOfScheduleChanges(doctor)
  +generateNotification()
}

Doctor --> Agenda : owns
Agenda --> WorkingHour : contains
Agenda --> UnavailablePeriod : contains
Agenda --> Appointment : displays
Doctor --> AgendaManager : uses
AgendaManager --> Agenda : modifies
AgendaManager --> Appointment : checks
```

---

## 5. Package : Paiement System

```mermaid
classDiagram
direction LR

class Patient {
  -int id
  -String fullName
  +viewBillingSummary()
  +selectPaymentMethod()
  +simulatePayment()
}

class Appointment {
  -int id
  -Date date
  -double basePrice
  -double finalPrice
  -String status
}

class Payment {
  -int id
  -String paymentMethod
  -double baseAmount
  -double insuranceCoverageAmount
  -double promotionAmount
  -double finalAmount
  -String paymentStatus
  -Date paymentDate
  +selectCreditCard()
  +selectInsuranceCoverage()
  +selectDigitalWallet()
  +calculateFinalPrice()
  +applyInsuranceCoverage(percent)
  +applyPromotion(value)
  +applyFixedDiscount(amount)
  +applyPercentageDiscount(percent)
  +simulatePayment()
  +confirmPayment()
  +recordPaymentOption()
}

class BillingSummary {
  -double serviceFee
  -double discount
  -double insuranceCoverage
  -double total
  +displaySummary()
}

class PaymentSystem {
  +simulatePayment(payment)
  +confirmPayment(payment)
}

class InsuranceProvider {
  -String name
  -double coverageRate
  +applyInsuranceCoverage(payment)
}

class Promotion {
  -String code
  -String type
  -double value
  +applyPromotion(payment)
}

Patient --> Payment : makes
Appointment --> Payment : requires
Payment --> BillingSummary : produces
Payment --> PaymentSystem : sends simulation
Payment --> InsuranceProvider : uses
Payment --> Promotion : applies
```

---

## 6. Package : Notification Subsystem

```mermaid
classDiagram
direction LR

class User {
  -int id
  -String fullName
  -String email
  -String phone
  -boolean emailNotifications
  -boolean smsNotifications
  -boolean inAppNotifications
  +manageNotificationPreferences()
}

class Patient {
  +receiveAppointmentConfirmation()
  +receiveAppointmentCancellation()
  +receiveAppointmentReminder()
  +receiveScheduleModificationNotification()
}

class Doctor {
  +receiveScheduleModificationNotification()
}

class Notification {
  -int id
  -String title
  -String message
  -String type
  -String channel
  -Date createdAt
  -boolean sent
  +generateNotification()
  +sendEmailNotification()
  +sendSMSNotification()
  +sendInAppNotification()
  +sendAppointmentConfirmation()
  +sendAppointmentCancellation()
  +sendAppointmentReminder()
  +sendScheduleModificationNotification()
}

class NotificationManager {
  -List~Notification~ notifications
  +generateNotification(user, type, message)
  +sendEmailNotification(notification)
  +sendSMSNotification(notification)
  +sendInAppNotification(notification)
  +sendAppointmentConfirmation(appointment)
  +sendAppointmentCancellation(appointment)
  +sendAppointmentReminder(appointment)
  +sendScheduleModificationNotification(appointment)
}

class Appointment {
  -int id
  -Date date
  -String status
}

User <|-- Patient
User <|-- Doctor
User --> NotificationManager : configures preferences
NotificationManager --> Notification : creates and sends
NotificationManager --> Appointment : uses event data
Notification --> User : sent to
```

---

## 7. Package : Administrator System

```mermaid
classDiagram
direction LR

class Administrator {
  -int id
  -String fullName
  -String email
  +manageUsers()
  +manageClinics()
  +manageHealthcareServices()
  +configureSystemSettings()
  +monitorAppointments()
  +viewSystemActivity()
}

class AdminPanel {
  -List~User~ users
  -List~Clinic~ clinics
  -List~HealthcareService~ services
  -List~Appointment~ appointments
  -SystemSettings settings
  +createUser(user)
  +modifyUser(user)
  +deleteUser(user)
  +suspendAccount(user)
  +assignRoles(user, role)
  +createClinic(clinic)
  +updateClinic(clinic)
  +deleteClinic(clinic)
  +addService(service)
  +updateService(service)
  +removeService(service)
  +assignDoctorToService(doctor, service)
  +managePricingRules()
  +managePromotions()
  +configureNotificationSystem()
  +monitorAppointments()
  +viewSystemActivity()
  +ensureDataConsistency()
}

class User {
  -int id
  -String fullName
  -String email
  -String role
  -String status
}

class Clinic {
  -int id
  -String name
  -String address
  -String city
}

class HealthcareService {
  -int id
  -String name
  -String specialty
  -double fee
}

class Doctor {
  -int id
  -String fullName
  -String specialty
}

class Appointment {
  -int id
  -Date date
  -String status
}

class SystemSettings {
  -double defaultInsuranceRate
  -double defaultPromotionRate
  -boolean emailEnabled
  -boolean smsEnabled
  -boolean inAppEnabled
  +updatePricingRules()
  +updatePromotionRules()
  +updateNotificationSettings()
}

Administrator --> AdminPanel : uses
AdminPanel --> User : manages
AdminPanel --> Clinic : manages
AdminPanel --> HealthcareService : manages
AdminPanel --> Doctor : assigns
AdminPanel --> Appointment : monitors
AdminPanel --> SystemSettings : configures
Clinic --> HealthcareService : offers
HealthcareService --> Doctor : assigned doctors
```

---

## 8. Vue globale simplifiée — avant SOLID/GRASP

```mermaid
classDiagram
direction LR

class HealthCarePlatform {
  -AuthManager authManager
  -ServiceCatalog serviceCatalog
  -AppointmentManager appointmentManager
  -AgendaManager agendaManager
  -PaymentSystem paymentSystem
  -NotificationManager notificationManager
  -AdminPanel adminPanel
  +registerUser(user)
  +login(email, password)
  +browseServices()
  +scheduleAppointment()
  +manageDoctorAgenda()
  +processPayment()
  +sendNotification()
  +manageAdministration()
}

class User {
  -int id
  -String fullName
  -String email
  -String password
  -String role
  -String status
}

class Patient {
  -String insuranceNumber
}

class Doctor {
  -String licenseNumber
  -String specialty
}

class Administrator {
  +manageSystem()
}

class Clinic {
  -int id
  -String name
  -String address
}

class HealthcareService {
  -int id
  -String name
  -String specialty
  -int durationMinutes
  -double fee
}

class Appointment {
  -int id
  -Date date
  -String startTime
  -String endTime
  -String status
  -double finalPrice
}

class Agenda {
  -List~WorkingHour~ workingHours
  -List~UnavailablePeriod~ unavailablePeriods
}

class Payment {
  -int id
  -String paymentMethod
  -double finalAmount
  -String paymentStatus
}

class Notification {
  -int id
  -String message
  -String type
  -String channel
  -boolean sent
}

class AuthManager {
  +registerAccount()
  +login()
  +logout()
}

class ServiceCatalog {
  +browseClinics()
  +searchServices()
  +filterServices()
}

class AppointmentManager {
  +scheduleAppointment()
  +modifyAppointment()
  +cancelAppointment()
  +confirmAppointment()
}

class AgendaManager {
  +defineWorkingHours()
  +updateAvailability()
  +blockUnavailablePeriods()
}

class PaymentSystem {
  +calculateFinalPrice()
  +simulatePayment()
  +confirmPayment()
}

class NotificationManager {
  +generateNotification()
  +sendNotification()
}

class AdminPanel {
  +manageUsers()
  +manageClinics()
  +manageHealthcareServices()
  +configureSystemSettings()
}

User <|-- Patient
User <|-- Doctor
User <|-- Administrator

HealthCarePlatform --> AuthManager
HealthCarePlatform --> ServiceCatalog
HealthCarePlatform --> AppointmentManager
HealthCarePlatform --> AgendaManager
HealthCarePlatform --> PaymentSystem
HealthCarePlatform --> NotificationManager
HealthCarePlatform --> AdminPanel

ServiceCatalog --> Clinic
Clinic --> HealthcareService
HealthcareService --> Doctor
Doctor --> Agenda
Appointment --> Patient
Appointment --> Doctor
Appointment --> HealthcareService
Appointment --> Payment
Appointment --> Notification
AppointmentManager --> Appointment
AgendaManager --> Agenda
PaymentSystem --> Payment
NotificationManager --> Notification
AdminPanel --> User
AdminPanel --> Clinic
AdminPanel --> HealthcareService
```

---

## Commentaire pour le rapport : limites volontaires de cette version

Cette version peut être utilisée comme **modèle initial avant amélioration**. Elle est correcte pour représenter les concepts métier principaux, mais elle présente volontairement plusieurs limites architecturales :

- Les classes `Manager` concentrent beaucoup de responsabilités.
- Les opérations métier sont souvent placées directement dans les entités.
- Les dépendances sont directes entre modules.
- Les variantes de paiement, de notification ou de promotion ne sont pas isolées dans des abstractions.
- Il n’y a pas de design pattern explicite.
- L’extension du système demanderait souvent de modifier les classes existantes.

Ces limites permettront de justifier clairement la version “après” basée sur SOLID, GRASP et les design patterns.

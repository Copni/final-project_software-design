# Applying GRASP/SOLID principles:
The initial AdminPanel had too many responsibilities, so it was split into specialized services.
This improves Single Responsibility and High Cohesion by grouping related operations together.
The Administrator now depends on dedicated controllers, reducing coupling with domain entities.
New domain objects such as Role, PricingRule and ActivityLog make the model more expressive.
These changes make the design easier to maintain, extend and test.
```mermaid
classDiagram
direction LR

class Administrator {
  +manageUsers()
  +manageClinics()
  +manageHealthcareServices()
  +configureSystemSettings()
  +monitorAppointments()
}

class AdminService {
  +createUser(data) User
  +modifyUser(userId, data)
  +deleteUser(userId)
  +suspendAccount(userId)
  +assignRole(userId, role)
}

class ClinicManagementService {
  +createClinic(data) Clinic
  +updateClinic(clinicId, data)
  +deleteClinic(clinicId)
}

class HealthcareServiceManagementService {
  +addService(clinicId, data) HealthcareService
  +updateService(serviceId, data)
  +removeService(serviceId)
  +assignDoctorToService(serviceId, doctorId)
}

class SystemConfigurationService {
  +managePricingRules(rules)
  +managePromotions(promotions)
  +configureNotificationSystem(config)
}

class AppointmentMonitoringService {
  +monitorAppointments() List~Appointment~
  +ensureDataConsistency() ConsistencyReport
}

class ActivityLogService {
  +recordAction(action)
  +viewSystemActivity() List~ActivityLog~
}

class SystemConfiguration {
  <<Singleton>>
  -SystemConfiguration instance
  -Map settings
  +getInstance() SystemConfiguration
  +setValue(key, value)
  +getValue(key)
}

class User {
  -UUID id
  -String email
  -AccountStatus status
}

class Role {
  -RoleType name
}

class Clinic {
  -UUID id
  -String name
  -String location
}

class HealthcareService {
  -UUID id
  -String name
  -Money fee
}

class Doctor {
  -UUID id
  -String fullName
  -String specialty
}

class Appointment {
  -UUID id
  -AppointmentStatus status
}

class PricingRule {
  <<interface>>
  +apply(amount, context) Money
}

class PromotionRule {
  +apply(amount, context) Money
}

class NotificationConfig {
  -bool emailEnabled
  -bool smsEnabled
  -bool inAppEnabled
}

class ActivityLog {
  -UUID id
  -String action
  -LocalDateTime timestamp
  -UUID actorId
}

class ConsistencyReport {
  -bool valid
  -List~String~ issues
}

Administrator ..> AdminService
Administrator ..> ClinicManagementService
Administrator ..> HealthcareServiceManagementService
Administrator ..> SystemConfigurationService
Administrator ..> AppointmentMonitoringService
Administrator ..> ActivityLogService
AdminService ..> User
AdminService ..> Role
ClinicManagementService ..> Clinic
HealthcareServiceManagementService ..> HealthcareService
HealthcareServiceManagementService ..> Doctor
HealthcareService "0..*" -- "1..*" Doctor
Clinic "1" *-- "0..*" HealthcareService
SystemConfigurationService ..> SystemConfiguration
SystemConfigurationService ..> PricingRule
SystemConfigurationService ..> PromotionRule
SystemConfigurationService ..> NotificationConfig
AppointmentMonitoringService ..> Appointment
AppointmentMonitoringService ..> ConsistencyReport
ActivityLogService ..> ActivityLog
```
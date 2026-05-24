# Applying SOLID/GRASP principles + Strategy pattern:
The Patient class was simplified to only represent patient data, improving cohesion and respecting SRP.
Search and browsing responsibilities were moved to ServiceCatalog, which acts as a GRASP Controller.
Filtering logic was extracted into separate Strategy classes to respect OCP and reduce coupling.
Each filter can now be added, removed, or extended without modifying the catalog logic.
```mermaid
classDiagram
direction LR

class Patient {
  -int id
  -String fullName
}

class Clinic {
  -int id
  -String name
  -String address
  -String city
  -String phone
  -List~HealthcareService~ services
  +addService(service)
  +getDetails()
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
}

class Doctor {
  -int id
  -String fullName
  -String specialty
  +getProfile()
  +getAvailableSlots()
}

class ServiceCatalog {
  -List~Clinic~ clinics
  -List~HealthcareService~ services
  -List~ServiceFilterStrategy~ filters
  +browseClinics()
  +browseServices()
  +searchServices(keyword)
  +applyFilters()
  +addFilter(filter)
  +clearFilters()
}

class ServiceFilterStrategy {
  <<interface>>
  +filter(services)
}

class SpecialtyFilterStrategy {
  -String specialty
  +filter(services)
}

class LocationFilterStrategy {
  -String location
  +filter(services)
}

class AvailabilityFilterStrategy {
  -Date date
  +filter(services)
}

Patient --> ServiceCatalog : uses
ServiceCatalog --> Clinic : contains
ServiceCatalog --> HealthcareService : contains
Clinic --> HealthcareService : offers
HealthcareService --> Doctor : assigned doctors

ServiceCatalog --> ServiceFilterStrategy : uses
ServiceFilterStrategy <|.. SpecialtyFilterStrategy
ServiceFilterStrategy <|.. LocationFilterStrategy
ServiceFilterStrategy <|.. AvailabilityFilterStrategy
```
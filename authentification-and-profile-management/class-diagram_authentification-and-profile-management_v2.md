# Applying SOLID/GRASP principles + factory pattern:
The model was refactored by moving authentication, profile management, session handling and role assignment out of User, applying SOLID SRP and GRASP High Cohesion.
Inheritance between User, Patient, Doctor and Administrator was replaced by a Role abstraction, improving SOLID OCP/LSP and GRASP Low Coupling.
Repositories and interfaces such as UserRepository and PasswordHasher were introduced to respect SOLID DIP and GRASP Protected Variations.
Responsibilities were assigned to the classes that own the right information, following GRASP Information Expert.
A Factory Method was added through RoleFactory to centralize role creation without modifying client services.
```mermaid
classDiagram
direction LR

class User {
  -int id
  -String email
  -String passwordHash
  -UserStatus status
  +isActive()
}

class UserProfile {
  -String firstName
  -String lastName
  -String phone
  -String address
  +updatePersonalInformation()
}

class Role {
  <<interface>>
  +getName()
  +getPermissions()
}

class PatientRole {
  -String insuranceNumber
  +getName()
  +getPermissions()
}

class DoctorRole {
  -String licenseNumber
  -String specialty
  +getName()
  +getPermissions()
}

class AdministratorRole {
  +getName()
  +getPermissions()
}

class RoleFactory {
  +createRole(roleType, roleData) Role
}

class AuthService {
  +registerAccount(registrationData)
  +login(email, password)
  +logout(session)
  +resetPassword(email)
}

class UserService {
  +updateProfile(userId, profileData)
  +changePassword(userId, oldPassword, newPassword)
}

class RoleService {
  +assignRole(userId, roleType, roleData)
  +removeRole(userId, role)
}

class SessionManager {
  +createSession(user)
  +invalidateSession(session)
  +validateSession(token)
}

class Session {
  -String token
  -Date startDate
  -Date expirationDate
  -boolean active
  +isValid()
}

class UserRepository {
  <<interface>>
  +findByEmail(email)
  +findById(id)
  +save(user)
}

class PasswordHasher {
  <<interface>>
  +hash(password)
  +verify(password, hash)
}

User "1" --> "1" UserProfile
User "1" --> "0..*" Role
User "1" --> "0..*" Session

Role <|.. PatientRole
Role <|.. DoctorRole
Role <|.. AdministratorRole

RoleFactory --> Role
RoleService --> RoleFactory
RoleService --> UserRepository

AuthService --> UserRepository
AuthService --> PasswordHasher
AuthService --> SessionManager

UserService --> UserRepository
SessionManager --> Session
```

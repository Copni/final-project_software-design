# Authentication and User Roles Java Prototype

This prototype illustrates the authentication, profile management, and role management subsystem from the UML class diagram.

## What it demonstrates

- **Role abstraction instead of inheritance**: `User` owns a list of `Role` objects instead of being subclassed as patient, doctor, or administrator.
- **Factory Method**: `RoleFactory` centralizes role creation.
- **SOLID SRP**: authentication, profile updates, role assignment, and session handling are separated into dedicated services.
- **SOLID DIP**: services depend on `UserRepository` and `PasswordHasher` interfaces.
- **GRASP Protected Variations**: repository and hashing implementation details are isolated behind interfaces.
- **GRASP High Cohesion / Low Coupling**: each class has a small, focused responsibility.

## Run

From this folder:

```bash
javac -d out src/userroles/*.java
java -cp out userroles.Demo
```

# HealthCare Appointment Management System
## Group
- Nicolas Papleux
- Arnaud Van Eeckhoven
- Romain Dangin
- Nail Fahim
- Yanis Zouitene

## Project Overview

This project models a healthcare appointment management platform as part of my Software Design course. Its goal is to design a software architecture divided into sub-projects, where each sub-project represents a functional domain of the platform as a microservice.

The platform allows:

- patients to browse clinics, healthcare services, and doctor profiles;
- patients to schedule, modify, or cancel appointments;
- doctors to manage their agenda and availability;
- the payment system to handle payment methods, insurance coverage, and promotions;
- the notification system to send confirmations, reminders, and cancellations;
- administrators to manage users, clinics, healthcare services, and global system settings.

The project is mainly composed of UML diagrams written with Mermaid. Each sub-project contains a use case diagram and one or more class diagrams.

## General Architecture

The system is divided into several business domains, designed as independent microservices. Each folder corresponds to a specific business responsibility.

This decomposition helps separate responsibilities, reduce coupling between parts of the system, and make it easier to apply software design principles studied in class, especially SOLID and GRASP.

## Sub-Project Decomposition

| Folder | Sub-project role |
| --- | --- |
| `overview` | Simplified global view of the system and the interactions between actors and subsystems. |
| `authentification-and-profile-management` | Handles authentication, registration, login, user profiles, and preferences. |
| `patient-service` | Allows patients to browse clinics, healthcare services, search filters, and doctor profiles. |
| `appointement-management` | Manages the appointment lifecycle: scheduling, modification, cancellation, history, and constraint validation. |
| `doctor-agenda-management` | Manages doctor agendas, working hours, availability, and unavailable periods. |
| `paiement-system` | Handles billing, payment methods, insurance coverage, promotions, and payment simulation. |
| `notification` | Generates and sends notifications by email, SMS, or in-app messages. |
| `administrator-system` | Manages users, clinics, healthcare services, pricing rules, promotions, and system settings. |
| `class_diagramms` | Contains general class diagrams or cross-project working versions. |

## File Organization

Each sub-project generally follows the same structure:

- a `use-case_*.md` file describing the use cases of the subsystem;
- a `class-diagram_*_v1.md` file representing the first version of the class diagram;
- a `class-diagram_*_v2.md` file representing an improved version after applying GRASP/SOLID principles.

The diagrams are written in Mermaid, which makes them directly viewable in tools that support Markdown and Mermaid.

## Diagram Version Convention

### Version `v1`

The `v1` version corresponds to the class diagram designed "on the fly".

This means that the first version represents an initial design created before deeper analysis and before the systematic application of SOLID and GRASP principles.

### Version `v2`

The `v2` version corresponds to the revised version of the class diagram, where SOLID and GRASP principles are applied in order to obtain a more maintainable and better-structured design.

## Recommended Reading Order

To understand the project, it is recommended to read the files in the following order:

1. `overview/use-case_general-simplified.md` to understand the global vision of the system.
2. The `use-case_*.md` files of each sub-project to understand the expected features.
3. The `v1` class diagrams to observe the initial design.
4. The `v2` class diagrams to see the improvements made with SOLID and GRASP.

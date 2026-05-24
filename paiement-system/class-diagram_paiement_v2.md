# Applying SOLID/GRASP principles = Strategy pattern:
The payment logic was removed from Patient and Payment to respect Single Responsibility and improve cohesion.
PaymentService now acts as a GRASP Controller that coordinates the payment process.
BillingCalculator is responsible for computing the final amount using dedicated strategies.
The Strategy Pattern is applied to payment methods, discounts, and insurance coverage to support polymorphism.
This makes the design easier to extend without modifying existing classes, respecting the Open/Closed Principle.
```mermaid
classDiagram
direction LR

class Patient {
  -int id
  -String fullName
}

class Appointment {
  -int id
  -Date date
  -double basePrice
  -AppointmentStatus status
}

class Payment {
  -int id
  -double baseAmount
  -double finalAmount
  -PaymentStatus status
  -Date paymentDate
}

class BillingSummary {
  -double serviceFee
  -double discount
  -double insuranceCoverage
  -double total
  +displaySummary()
}

class PaymentService {
  +createPayment(patient, appointment)
  +simulatePayment(payment, paymentStrategy)
  +confirmPayment(payment, paymentStrategy)
}

class BillingCalculator {
  +calculate(payment, discountStrategy, insuranceStrategy) BillingSummary
}

class PaymentStrategy {
  <<interface>>
  +simulate(amount)
  +pay(amount)
}

class CreditCardPaymentStrategy {
  +simulate(amount)
  +pay(amount)
}

class DigitalWalletPaymentStrategy {
  +simulate(amount)
  +pay(amount)
}

class InsurancePaymentStrategy {
  +simulate(amount)
  +pay(amount)
}

class DiscountStrategy {
  <<interface>>
  +apply(amount) double
}

class FixedDiscountStrategy {
  -double amount
  +apply(amount) double
}

class PercentageDiscountStrategy {
  -double percent
  +apply(amount) double
}

class PromotionDiscountStrategy {
  -String code
  -double value
  +apply(amount) double
}

class InsuranceStrategy {
  <<interface>>
  +applyCoverage(amount) double
}

class StandardInsuranceStrategy {
  -double coverageRate
  +applyCoverage(amount) double
}

Patient --> Appointment : books
Appointment --> Payment : requires
PaymentService --> Payment : manages
PaymentService --> PaymentStrategy : uses
BillingCalculator --> DiscountStrategy : uses
BillingCalculator --> InsuranceStrategy : uses
Payment --> BillingSummary : produces

PaymentStrategy <|.. CreditCardPaymentStrategy
PaymentStrategy <|.. DigitalWalletPaymentStrategy
PaymentStrategy <|.. InsurancePaymentStrategy

DiscountStrategy <|.. FixedDiscountStrategy
DiscountStrategy <|.. PercentageDiscountStrategy
DiscountStrategy <|.. PromotionDiscountStrategy

InsuranceStrategy <|.. StandardInsuranceStrategy
```
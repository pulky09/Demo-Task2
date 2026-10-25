Feature: Payments API

    Scenario: Create a payment
      When I create a payment for 100 CAD
      Then the payment should be created successfully

    Scenario: Complete the payment lifecycle
      Given a payment is created
      When I authorize and settle the payment
      Then the payment should be settled successfully

    Scenario: Refund the payment
      When I refund the payment
      Then then payment should be refunded successfully

    Scenario: Reject invalid refund amount
      When I request a refund of 150 CAD
      Then the request should fail and error message should indicate that refund amount exceeds

    Scenario: Prevent duplicate payment creation using the same idempotency key
      Given I have a payment request for 100 CAD with an idempotency key
      When I create a payment for 100 CAD
      And I submit the same request again with the same idempotency key
      Then only one payment should be created and both responses should return the same payment ID


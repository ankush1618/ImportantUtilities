@All
Feature: Uber Booking Feature

  @Smoke
  Scenario: Booking Cab Sedan
    Given User want to select car type "Sedan" from Uber App
    When User selects car "Sedan" and pick up point "Noida" and drop location "Hyderabad"
    Then Driver starts the Journey
    And Driver ends the journey
    Then User pays 10000 rupees

  @Regression
  Scenario: Booking Cab Hatchback
    Given User want to select car type "Hatchback" from Uber App
    When User selects car "Hatchback" and pick up point "Noida" and drop location "Pathankot"
    Then Driver starts the Journey
    And Driver ends the journey
    Then User pays 50000 rupees

  @Prod @Smoke
  Scenario: Booking Cab SUV
    Given User want to select car type "SUV" from Uber App
    When User selects car "Suv" and pick up point "Gurdaspur" and drop location "Pathankot"
    Then Driver starts the Journey
    And Driver ends the journey
    Then User pays 80000 rupees
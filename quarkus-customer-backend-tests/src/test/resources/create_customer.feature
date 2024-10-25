Feature: Create Customer

  Scenario: Create a customer
    Given I have a REST client for customers
    When I create a customer with the following data:
      | name  | email                | phone_number | address |
      | "Bender Rodriguez"  | "bender@planetexpress.com" | "1234567890" | "West 57th Street and, most likely, Henry Hudson Parkway, Manhattan, New New York, United States, Earth, Sol System, Milky Way, Universe A" |
    Then I should receive a created response
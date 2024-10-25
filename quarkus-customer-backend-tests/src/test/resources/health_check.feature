Feature: Simple Health Check feature towards the Customer API

    Scenario: Health Check
      Given I have a REST client for customers
      When I request the health check
      Then I should receive a successful response
      And the health check should exist
      And the health check should contain "status"
      And the health check should contain "UP"

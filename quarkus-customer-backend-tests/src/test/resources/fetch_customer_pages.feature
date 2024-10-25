Feature: Fetch customer pages test

  Scenario Outline: Fetch <size> customers in page <page>
    Given I have a REST client for customers
    When I request <size> customers in <page> page
    Then I should receive a successful response
    And the customers should exist
    And the customers should contain <size> customers

    Examples:
      | size | page |
      | 5    | 1    |
      | 10   | 2    |
      | 20   | 3    |

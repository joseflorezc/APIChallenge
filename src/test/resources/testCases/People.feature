Feature: People
  Scenario: People Details
    Given Person id is known
    And the api key is provided
    When the request to see detail is sent
    Then the detail of the person requested is retrieved


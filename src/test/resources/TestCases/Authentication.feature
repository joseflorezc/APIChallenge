Feature: User Authentication

  Scenario: User Guest Session
    Given guest session isn't created
    When Requests a Guest Session
    Then the Guest Session is created


  Scenario: Create Request Token
    Given
    When the token is requested
    Then the token is created succesfully


  Scenario: Create Session ID
    Given the token request was successful
    And successfully created session id with login
    When the session id is requested
    Then a session id is provided


  Scenario: Create Session ID with Login
    Given the Username and Password are provided
    And the Request Token is Stored
    When the session id with login is requested
    Then the request was successful


  Scenario: Delete Session
    Given the session id is known
    When the request of deleting is sent
    Then the session is deleted successfully


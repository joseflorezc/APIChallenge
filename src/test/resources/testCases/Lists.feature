Feature:Lists of movies of the User

  Scenario: List Detail
    Given the user session is connected
    And at least one list is created
    And an api key is provided
    When the request of list detail is sent
    Then successfully the detail of the list is retrieved

  Scenario: Creating List Of Movie
    Given a name, description and language are provided
    And a session id is stored
    And an api key is provided
    When Requests of creating a list is sent
    Then the list is created successfully

  Scenario: Adding Movie To List
    Given a movie is provided
    And the list selected by the user exists
    And a session id is stored
    And an api key is provided
    When the request that adds the movie to the list is sent
    Then Movie is added to the list successfully


  Scenario: Clearing Movies from List
    Given existing list is provided
    And a session id is stored
    And an api key is provided
    When Request of clearing movie is sent
    Then the list is cleared successfully

  Scenario: Delete List
    Given an existing list is provided
    And a session id is stored
    And an api key is provided
    When a requests to delete the list
    Then a successful response is received




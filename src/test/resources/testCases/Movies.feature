Feature: Movies
  Scenario: Movie Details
    Given a movie is provided
    And the api key is provided
    When the request to see detail is sent
    Then the movie requested is retrieved

  Scenario: Rate a Movie
    Given a movie is provided
    And the api key is provided
    And a rate is provided
    When the rate request is sent
    Then the rate is uploaded successfully

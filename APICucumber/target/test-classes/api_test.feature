Feature: Petstore API Testing

  Scenario: Retrieve pet details and validate response
    Given I set the base URI to "https://petstore.swagger.io/v2"
    When I send a GET request to "/pet/123"
    Then the response status code should be 200
    And the pet ID should be 123
    And the pet name should be "doggie"
    And the pet status should be "available"
    And the category ID should be 0
    And the category name should be "string"
    And the first tag name should be "string"
    And I print the response body

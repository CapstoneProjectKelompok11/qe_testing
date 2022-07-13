Feature: Building
  As an admin
  I want to get data building
  So that I can see list of building and access the detail data building

  Scenario Outline: GET - As an admin I have to be able to get data building
    Given I set an endpoint for GET building
    When I request GET detail building with input <id>
    Then I validate the status code for get building is <statusCode>
    And validate the "<message>" and data details of building
    Examples:
      | id    | statusCode | message      |
      | 0     | 400        | not found    |
      | 99    | 400        | not found    |
      | 1     | 200        | success      |
#      | 15    | 200        | success      |
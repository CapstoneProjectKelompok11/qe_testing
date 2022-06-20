Feature: Floor
  As an admin
  I want to get data floor
  So that I can see list of floor and access the detail data floor

  Scenario Outline: GET - As an admin I have to be able to get data floor
    Given I set an endpoint for GET floor
    When I request GET detail floor with input <buildingId>
    Then I validate the status code for get floor is <statusCode>
    And validate the "<message>" and data details of floor
    Examples:
      | buildingId | statusCode | message      |
      | 0          | 400        | not found    |
      | 99         | 400        | not found    |
      | 1          | 200        | success      |
      | 9          | 200        | success      |
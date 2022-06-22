Feature: Review
  As an admin
  I want to get data review
  So that I can see list of review of building

  Scenario Outline: GET - As an admin I have to be able to get data review
    Given I set an endpoint for GET review
    When I request GET detail review with input <buildingId>, <page>, and <limit>
    Then I validate the status code for get review is <statusCode>
    And validate the "<message>" and data details of review
    Examples:
      | buildingId | page | limit | statusCode | message   |
      | 0          | 0    | 0     | 500        | error     |
      | 0          | 1    | 1     | 200        | not found |
      | 1          | 0    | 2     | 200        | success   |
      | 2          | 2    | 0     | 500        | error     |
      | 3          | 3    | 3     | 200        | not found |
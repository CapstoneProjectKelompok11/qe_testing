Feature: Get Building with input Search
  As an admin
  I want to get data building by search
  So that I can see list of building and access the detail data building

  Scenario Outline: GET - As an admin I have to be able to get data building
    Given I set an endpoint for GET building by search
    When I request GET detail building with input "<name>"
    Then I validate the status code for get building by search is <statusCode>
    And validate the "<message>" and data details of building by search
    Examples:
      | name              | statusCode | message      |
      |                   | 200        | get all data |
      | Grand Depok City  | 200        | data null    |
      | Senayan City      | 200        | success      |
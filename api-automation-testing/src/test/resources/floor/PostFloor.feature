Feature: Floor
  As an admin
  I want to create data floor
  So that I can add data floor of building

  Background:
    Given I have logged in as admin
    And I get token from the response

  Scenario Outline: POST - As an admin I have to be able to create data floor of building
    Given I set an endpoint for POST floor
    When I request POST detail floor with "<statusAuthorize>", input <buildingId>, "<name>", "<type>", "<floor_size>", <max_capacity>, <starting_price> and "<facilities>"
    Then I validate the status code for post floor is <statusCode>
    And validate the "<message>" and data details after post floor
    Examples:
      | statusAuthorize | buildingId | name         | type            | floor_size | max_capacity | starting_price | facilities | statusCode | message      |
      | not authorized  | 0          |              |                 |            | 0            | 0              |            | 403        | unauthorized |
      | authorized      | 0          |              |                 |            | 0            | 0              |            | 400        | not found    |
      | authorized      | 0          | Floor 48th   | Serviced Office | 320.20 sqm | 8            | 2300000        | PROJECTOR  | 400        | not found    |
#      | authorized      | 1         |              | existed | existed     | existed       | 20       | 500        | error        |
#      | authorized      | 2         | Equity Tower |         | existed     | existed       | 30       | 500        | error        |
#      | authorized      | 3         | Equity Tower | existed |             | existed       | 30       | 500        | error        |
#      | authorized      | 4         | Equity Tower | existed | existed     |               | 30       | 500        | error        |
#      | authorized      | 5         | Equity Tower | existed | existed     | existed       | 0        | 500        | error        |
      | authorized      | 99         | Floor 48th   | Serviced Office | 320.20 sqm | 8            | 2300000        | PROJECTOR  | 400        | not found    |
#      | authorized      | 10        | Equity Tower | existed | existed     | existed       | 50       | 200        | success      |
#      | authorized      | 1         | new          | new     | new         | new           | 25       | 200        | success      |
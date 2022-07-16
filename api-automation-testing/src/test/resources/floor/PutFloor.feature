Feature: Edit Data Floor
  As an admin
  I want to edit data floor
  So that I can change data of floor

  Background:
    Given I have logged in as admin
    And I get token admin from the response

  Scenario Outline: PUT - As an admin I have to be able to edit data floor
    Given I set an endpoint for PUT edit floor
    When I request PUT detail edit floor with "<statusAuthorize>", input <floorId>, "<name>", "<type>", "<floor_size>", <max_capacity>, and <starting_price>
    Then I validate the status code for put edit floor is <statusCode>
    And validate the "<message>" and data details after edit floor
    Examples:
      | statusAuthorize | floorId | name         | type            | floor_size | max_capacity | starting_price | statusCode | message      |
      | not authorized  | 0       |              |                 |            | 0            | 0              | 403        | unauthorized |
      | authorized      | 0       |              |                 |            | 0            | 0              | 400        | not found    |
      | authorized      | 0       | Floor 48th   | Serviced Office | 320.20 sqm | 8            | 2300000        | 400        | not found    |
      | authorized      | 42      |              | Serviced Office | 320.20 sqm | 8            | 2300000        | 200        | success      |
      | authorized      | 42      | Floor 48th   |                 | 320.20 sqm | 8            | 2300000        | 200        | success      |
      | authorized      | 42      | Floor 48th   | Serviced Office |            | 8            | 2300000        | 200        | success      |
      | authorized      | 42      | Floor 48th   | Serviced Office | 320.20 sqm | 0            | 2300000        | 200        | success      |
      | authorized      | 42      | Floor 48th   | Serviced Office | 320.20 sqm | 8            | 0              | 200        | success      |
      | authorized      | 99      | Floor 48th   | Serviced Office | 320.20 sqm | 8            | 2300000        | 400        | not found    |
      | authorized      | 42      | Equity Tower | Serviced Office | 320.20 sqm | 20           | 3750000        | 200        | success      |
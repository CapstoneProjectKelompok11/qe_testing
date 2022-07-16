#Feature: Edit Data Building
#  As an admin
#  I want to edit data building
#  So that I can change data of building
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: PUT - As an admin I have to be able to edit data building
#    Given I set an endpoint for PUT edit building
#    When I request PUT detail edit building with "<statusAuthorize>", input <buildingId>, "<name>", "<address>", "<description>", "<building_size>", and <capacity>
#    Then I validate the status code for put edit building is <statusCode>
#    And validate the "<message>" and data details after edit building
#    Examples:
#      | statusAuthorize | buildingId | name            | address  | description | building_size | capacity | statusCode | message      |
#      | not authorized  | 27         |                 |          |             |               | 0        | 403        | unauthorized |
#      | authorized      | 0          |                 |          |             |               | 0        | 400        | not found    |
#      | authorized      | 0          | Equity Tower    | existed  | existed     | existed       | 10       | 400        | not found    |
##      | authorized      | 27         |                 | existed  | existed     | existed       | 20       | 500        | error        |
##      | authorized      | 27         | Equity Tower    |          | existed     | existed       | 30       | 500        | error        |
##      | authorized      | 27         | Equity Tower    | existed  |             | existed       | 30       | 500        | error        |
##      | authorized      | 27         | Equity Tower    | existed  | existed     |               | 30       | 500        | error        |
##      | authorized      | 27         | Equity Tower    | existed  | existed     | existed       | 0        | 500        | error        |
#      | authorized      | 99         | Equity Tower    | existed  | existed     | existed       | 30       | 400        | not found    |
##      | authorized      | 27         | Equity Tower    | existed  | existed     | existed       | 50       | 200        | success      |
#      | authorized      | 27         | new             | new      | new         | new           | 30       | 200        | success      |
##      | authorized      | 28         | Graha Persada 2 | addGraha | descGraha   | sizeGraha     | 30       | 200        | success      |
#Feature: Create Reservation User
#  As a user
#  I want to create reservation
#  So that I can add data reservation of building
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario Outline: POST - As a user I have to be able to create data reservation
#    Given I set an endpoint for POST reservation user
#    When I request POST detail reservation user with "<statusAuthorize>", input <floorId>, "<startReservation>", "<company>", "<phone>", <participant>, and "<note>"
#    Then I validate the status code for post reservation user is <statusCode>
#    And validate the "<message>" and data details after post reservation user
#    Examples:
#      | statusAuthorize | floorId | startReservation | company | phone   | participant | note | statusCode | message      |
#      | not authorized  | 42      | existed          | Alterra | existed | 10          | note | 403        | unauthorized |
#      | authorized      | 0       |                  |         |         | 0           |      | 400        | not found    |
#      | authorized      | 0       | existed          | Alterra | existed | 10          | note | 400        | not found    |
##      | authorized      | 42      |                  | Alterra | existed | 10          | note | 200        | success      |
##      | authorized      | 42      | existed          |         | existed | 10          | note | 200        | success      |
##      | authorized      | 42      | existed          | Alterra |         | 10          | note | 200        | success      |
##      | authorized      | 42      | existed          | Alterra | existed | 0           | note | 200        | success      |
##      | authorized      | 42      | existed          | Alterra | existed | 10          |      | 200        | success      |
#      | authorized      | 99      | existed          | Alterra | existed | 10          | note | 400        | not found    |
#      | authorized      | 42      | new              | Alterra | new     | 5           | note | 200        | success      |
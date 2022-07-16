#Feature: Create Reservation Admin
#  As an admin
#  I want to create reservation
#  So that I can add data reservation of building
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: POST - As an admin I have to be able to create data reservation
#    Given I set an endpoint for POST reservation admin
#    When I request POST detail reservation admin with "<statusAuthorize>", input "<reservationId>", <floorId>, "<startReservation>", "<company>", <price>, "<phone>", <participant>, and "<note>"
#    Then I validate the status code for post reservation admin is <statusCode>
#    And validate the "<message>" and data details after post reservation admin
#    Examples:
#      | statusAuthorize | reservationId | floorId | startReservation | company | price    | phone        | participant | note | statusCode | message      |
#      | not authorized  | existed       | 42      | valid format     | Alterra | 5000000  | 081239283744 | 10          | note | 403        | unauthorized |
#      | authorized      | null          | 0       |                  |         | 0        |              | 0           |      | 400        | not found    |
#      | authorized      | null          | 42      | valid format     | Alterra | 5000000  | 081239283744 | 10          | note | 400        | not found    |
#      | authorized      | existed       | 0       | valid format     | Alterra | 5000000  | 081239283744 | 10          | note | 400        | not found    |
#      | authorized      | existed       | 42      |                  | Alterra | 5000000  | 081239283744 | 10          | note | 500        | error        |
#      | authorized      | existed       | 42      | invalid format   | Alterra | 5000000  | 081239283744 | 10          | note | 400        | bad request  |
##      | authorized      | existed       | 42      | valid format     |         | 5000000  | 081239283744 | 10          | note | 200        | success      |
##      | authorized      | existed       | 42      | valid format     | Alterra | 5000000  | 081239283744 | 10          | note | 200        | success      |
##      | authorized      | existed       | 42      | valid format     | Alterra | 0        | 081239283744 | 10          | note | 200        | success      |
##      | authorized      | existed       | 42      | valid format     | Alterra | 5000000  |              | 10          | note | 200        | success      |
##      | authorized      | existed       | 42      | valid format     | Alterra | 5000000  | 081239283744 | 0           | note | 200        | success      |
##      | authorized      | existed       | 42      | valid format     | Alterra | 5000000  | 081239283744 | 10          |      | 200        | success      |
#      | authorized      | existed       | 99      | valid format     | Alterra | 5000000  | 081239283744 | 10          | note | 400        | not found    |
#      | authorized      | not existed   | 42      | valid format     | Alterra | 5000000  | 081239283744 | 10          | note | 400        | not found    |
##      | authorized      | existed       | 42      | valid format     | Alterra | 5000000  | 081239283744 | 10          | note | 200        | success      |
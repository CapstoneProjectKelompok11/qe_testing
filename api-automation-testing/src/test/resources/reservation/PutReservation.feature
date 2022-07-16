#Feature: Edit Status Reservation
#  As an admin
#  I want to edit status reservation
#  So that I can change reservation status
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: PUT - As a user I have to be able to edit status reservation
#    Given I set an endpoint for PUT edit status reservation
#    When I request PUT detail edit status reservation with "<statusAuthorize>", "<token>", input "<reservationId>", and "<status>"
#    Then I validate the status code for PUT edit status reservation is <statusCode>
#    And validate the "<message>" and data details after edit status reservation
#    Examples:
#      | statusAuthorize | token   | reservationId | status    | statusCode | message      |
#      | not authorized  |         | existed       | PENDING   | 403        | unauthorized |
#      | authorized      | null    | existed       | ACTIVE    | 403        | unauthorized |
#      | authorized      | invalid | existed       | WAITING   | 403        | unauthorized |
#      | authorized      | valid   | null          |           | 400        | bad request  |
#      | authorized      | valid   | null          | CANCELED  | 400        | not found    |
#      | authorized      | valid   | existed       |           | 400        | bad request  |
#      | authorized      | valid   | not existed   | COMPLETED | 400        | not found    |
##      | authorized      | valid   | existed       | PENDING   | 200        | success      |
##      | authorized      | valid   | existed       | ACTIVE    | 200        | success      |
##      | authorized      | valid   | existed       | WAITING   | 200        | success      |
##      | authorized      | valid   | existed       | CANCELED  | 200        | success      |
##      | authorized      | valid   | existed       | COMPLETED | 200        | success      |

#      | not authorized  |         | existed       | PENDING   | 403        | unauthorized |
#      | authorized      | valid   | null          |           | 400        | bad request  |
#      | authorized      | valid   | null          | CANCELED  | 400        | not found    |
#      | authorized      | valid   | existed       |           | 400        | bad request  |
#      | authorized      | valid   | not existed   | COMPLETED | 400        | not found    |
#      | authorized      | valid   | existed       | PENDING   | 200        | success      |
#      | authorized      | valid   | existed       | ACTIVE    | 200        | success      |
#      | authorized      | valid   | existed       | WAITING   | 200        | success      |
#      | authorized      | valid   | existed       | CANCELED  | 200        | success      |
#      | authorized      | valid   | existed       | COMPLETED | 200        | success      |
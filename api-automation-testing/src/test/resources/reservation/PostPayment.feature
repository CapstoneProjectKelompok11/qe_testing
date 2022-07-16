#Feature: Send Payment Reservation
#  As a user
#  I want to send payment reservation
#  So that I can add data reservation
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario Outline: POST - As a user I have to be able to send payment reservation
#    Given I set an endpoint for POST send payment reservation
#    When I request POST detail send payment reservation with "<statusAuthorize>", input "<reservationId>", and "<image>"
#    Then I validate the status code for post send payment reservation is <statusCode>
#    And validate the "<message>" and data details after post send payment reservation
#    Examples:
#      | statusAuthorize | reservationId | image          | statusCode | message      |
#      | not authorized  | existed       | valid format   | 403        | unauthorized |
##      | authorized      | null          | null           | 400        | not found    |
#      | authorized      | null          | valid format   | 400        | not found    |
##      | authorized      | existed       | null           | 400        | not found    |
#      | authorized      | not existed   | valid format   | 400        | not found    |
#      | authorized      | existed       | invalid format | 400        | not found    |
#      | authorized      | existed       | valid format   | 200        | success      |
#Feature: Cancel Reservation
#  As a user
#  I want to cancel reservation
#  So that I can remove reservation in list
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario Outline: PUT - As a user I have to be able to cancel reservation
#    Given I set an endpoint for PUT cancel reservation
#    When I request PUT detail cancel reservation with "<statusAuthorize>" and input "<reservationId>"
#    Then I validate the status code for cancel reservation is <statusCode>
#    And validate the "<message>" and data details after cancel reservation
#    Examples:
#      | statusAuthorize | reservationId | statusCode | message      |
#      | not authorized  | existed       | 403        | unauthorized |
#      | authorized      | null          | 400        | not found    |
#      | authorized      | not existed   | 400        | not found    |
#      | authorized      | existed       | 200        | success      |
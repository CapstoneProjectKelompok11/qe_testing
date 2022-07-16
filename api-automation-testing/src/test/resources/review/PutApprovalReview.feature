#Feature: Approval Review
#  As an admin
#  I want to approval review
#  So that I can add data review of building
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: PUT - As an admin I have to be able to approval review
#    Given I set an endpoint for PUT approval review
#    When I request PUT detail approval review with "<statusAuthorize>" and input "<userId>", <buildingId>, and "<approved>"
#    Then I validate the status code for put approval review is <statusCode>
#    And validate the "<message>" and data details after put approval review
#    Examples:
#      | statusAuthorize | userId  | buildingId | approved | statusCode | message      |
#      | not authorized  |         | 0          |          | 403        | unauthorized |
#      | authorized      |         | 0          |          | 400        | not found    |
#      | authorized      |         | 5          | true     | 400        | not found    |
#      | authorized      | existed | 0          | true     | 400        | not found    |
#      | authorized      | existed | 5          |          | 400        | not found    |
#      | authorized      | existed | 99         | true     | 400        | not found    |
#      | authorized      | existed | 8          | false    | 200        | success      |
#      | authorized      | existed | 5          | true     | 200        | success      |
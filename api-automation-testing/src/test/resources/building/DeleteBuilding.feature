#Feature: Delete Building
#  As an admin
#  I want to delete building
#  So that I can remove building from database
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: DELETE - As an admin I have to be able to delete building
#    Given I set an endpoint for DELETE building
#    When I request DELETE detail building with "<statusAuthorize>" and input <buildingId>
#    Then I validate the status code for delete building is <statusCode>
#    And validate the "<message>" and data details after delete building
#    Examples:
#      | statusAuthorize | buildingId | statusCode | message      |
#      | not authorized  | 0          | 403        | unauthorized |
#      | authorized      | 0          | 400        | not found    |
#      | authorized      | 99         | 400        | not found    |
#      | authorized      | 23         | 200        | success      |
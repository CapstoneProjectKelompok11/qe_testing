#Feature: Create Favorite Building
#  As a user
#  I want to create favorite of building
#  So that I can add favorite building
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario Outline: POST - As a user I have to be able to crete favorite of building
#    Given I set an endpoint for POST favorite
#    When I request POST detail favorite with "<statusAuthorize>" and input <buildingId>
#    Then I validate the status code for post favorite is <statusCode>
#    And validate the "<message>" and data details after post favorite
#    Examples:
#      | statusAuthorize | buildingId | statusCode | message      |
#      | not authorized  | 0          | 403        | unauthorized |
#      | authorized      | 0          | 400        | not found    |
#      | authorized      | 99         | 400        | not found    |
#      | authorized      | 1          | 500        | bad request  |
#      | authorized      | 7          | 200        | success      |
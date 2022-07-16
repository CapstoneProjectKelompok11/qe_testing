#Feature: Get Favorite Building
#  As an admin
#  I want to get favorite building
#  So that I can see list of building that I have favorite
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario Outline: GET - As an admin I have to be able to get favorite building
#    Given I set an endpoint for GET favorite building
#    When I request GET detail favorite building with "<statusAuthorize>" and "<token>"
#    Then I validate the status code for get favorite building is <statusCode>
#    And validate the "<message>" and data details of favorite building
#    Examples:
#      | statusAuthorize | token   | statusCode | message      |
#      | not authorized  |         | 403        | unauthorized |
##      | authorized      | null | 403        | unauthorized |
#      | authorized      | invalid | 403        | unauthorized |
#      | authorized      | valid   | 200        | success      |
#Feature: Get Profile
#  As a user
#  I want to get data profile
#  So that I can see list data of user
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario Outline: GET - As a user I have to be able to get data profile
#    Given I set an endpoint for GET profile
#    When I request GET detail profile with "<statusAuthorize>" and "<token>"
#    Then I validate the status code for get profile is <statusCode>
#    And validate the "<message>" and data details of profile
#    Examples:
#      | statusAuthorize | token   | statusCode | message      |
#      | not authorized  |         | 403        | unauthorized |
#      | authorized      |         | 403        | unauthorized |
#      | authorized      | invalid | 403        | unauthorized |
#      | authorized      | valid   | 200        | success      |
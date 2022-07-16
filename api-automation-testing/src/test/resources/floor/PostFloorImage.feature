#Feature: Create Floor Image
#  As an admin
#  I want to create data floor image
#  So that I can add data image of floor
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: POST - As an admin I have to be able to create floor image
#    Given I set an endpoint for POST floor image
#    When I request POST detail floor image with "<statusAuthorize>", input <floorId>, and "<image>"
#    Then I validate the status code for post floor image is <statusCode>
#    And validate the "<message>" and data details after post floor image
#    Examples:
#      | statusAuthorize | floorId | image          | statusCode | message      |
#      | not authorized  | 42      | valid format   | 403        | unauthorized |
##      | authorized      | 0       |                | 400        | not found    |
#      | authorized      | 0       | valid format   | 400        | not found    |
##      | authorized      | 42      |                | 400        | not found    |
#      | authorized      | 99      | valid format   | 400        | not found    |
##      | authorized      | 42      | invalid format | 200        | success      |
##      | authorized      | 42      | valid format   | 200        | success      |
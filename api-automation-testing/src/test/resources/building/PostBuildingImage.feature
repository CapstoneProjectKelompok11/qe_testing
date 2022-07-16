#Feature: Create Building Image
#  As an admin
#  I want to create data building image
#  So that I can add data image of building
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: POST - As an admin I have to be able to create building image
#    Given I set an endpoint for POST building image
#    When I request POST detail building image with "<statusAuthorize>", input <buildingId>, and "<image>"
#    Then I validate the status code for post building image is <statusCode>
#    And validate the "<message>" and data details after post building image
#    Examples:
#      | statusAuthorize | buildingId | image          | statusCode | message      |
#      | not authorized  | 28         | valid format   | 403        | unauthorized |
##      | authorized      | 0          |                | 400        | not found    |
#      | authorized      | 0          | valid format   | 400        | not found    |
##      | authorized      | 27         |                | 400        | not found    |
#      | authorized      | 99         | valid format   | 400        | not found    |
#      | authorized      | 27         | invalid format | 200        | success      |
#      | authorized      | 28         | valid format   | 200        | success      |
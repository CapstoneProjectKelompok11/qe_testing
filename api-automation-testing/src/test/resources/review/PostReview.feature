#Feature: Review
#  As an user
#  I want to create data review
#  So that I can add data review of building
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario Outline: POST - As an user I have to be able to create data review of building
#    Given I set an endpoint for POST review
##    When I request POST detail review with "<statusAuthorize>" and input <buildingId>, "<review>", and <rating>
#    When I request POST detail review with "<statusReview>", "<statusAuthorize>" and input <buildingId>, "<review>", and <rating>
#    Then I validate the status code for post review is <statusCode>
#    And validate the "<message>" and data details after post review
#    Examples:
#      | statusReview | statusAuthorize | buildingId | review | rating | statusCode | message      |
#      |              | not authorized  | 0          |        | 0      | 403        | unauthorized |
#      |              | authorized      | 0          |        | 0      | 400        | not found    |
#      |              | authorized      | 0          | bad    | 1      | 400        | not found    |
#      |              | authorized      | 1          |        | 2      | 500        | error        |
#      |              | authorized      | 2          | good   | 0      | 500        | error        |
#      |              | authorized      | 99         | good   | 3      | 400        | not found    |
#      | existed      | authorized      | 10         | good   | 6      | 200        | success      |
#      | existed      | authorized      | 3          | bad    | 2      | 200        | success      |
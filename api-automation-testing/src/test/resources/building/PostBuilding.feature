#Feature: Building
#  As an admin
#  I want to create data building
#  So that I can add data building of complex
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: POST - As an admin I have to be able to create data building
#    Given I set an endpoint for POST building
#    When I request POST detail building with "<statusAuthorize>", input <complexId>, "<name>", "<address>", "<description>", "<building_size>", and <capacity>
#    Then I validate the status code for post building is <statusCode>
#    And validate the "<message>" and data details after post building
#    Examples:
#      | statusAuthorize | complexId | name         | address | description | building_size | capacity | statusCode | message      |
#      | not authorized  | 0         |              |         |             |               | 0        | 403        | unauthorized |
#      | authorized      | 0         |              |         |             |               | 0        | 400        | not found    |
#      | authorized      | 0         | Equity Tower | existed | existed     | existed       | 10       | 400        | not found    |
##      | authorized      | 1         |              | existed | existed     | existed       | 20       | 500        | error        |
##      | authorized      | 2         | Equity Tower |         | existed     | existed       | 30       | 500        | error        |
##      | authorized      | 3         | Equity Tower | existed |             | existed       | 30       | 500        | error        |
##      | authorized      | 4         | Equity Tower | existed | existed     |               | 30       | 500        | error        |
##      | authorized      | 5         | Equity Tower | existed | existed     | existed       | 0        | 500        | error        |
#      | authorized      | 99        | Equity Tower | existed | existed     | existed       | 40       | 400        | not found    |
##      | authorized      | 10        | Equity Tower | existed | existed     | existed       | 50       | 200        | success      |
##      | authorized      | 1         | new          | new     | new         | new           | 25       | 200        | success      |
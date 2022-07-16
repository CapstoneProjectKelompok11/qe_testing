#Feature: Get Buildings
#  As an admin
#  I want to get data building
#  So that I can see list of building and access the detail data building
#
#  Scenario Outline: GET - As an admin I have to be able to get data building
#    Given I set an endpoint for GET buildings
#    When I request GET detail buildings with input <complexId>, <page>, and <limit>
#    Then I validate the status code for get buildings is <statusCode>
#    And validate the "<message>" and data details of buildings
#    Examples:
#      | complexId | page | limit | statusCode | message      |
#      | 0         | 0    | 0     | 500        | error        |
#      | 0         | 1    | 1     | 200        | data null    |
#      | 1         | 0    | 1     | 200        | data null    |
#      | 1         | 1    | 0     | 500        | error        |
##      | 1         | 0    | 0     | 500        | error        |
##      | 0         | 1    | 0     | 500        | error        |
##      | 0         | 0    | 1     | 200        | data null    |
#      | 99        | 1    | 1     | 200        | data null    |
#      | 1         | 1    | 1     | 200        | data null    |
Feature: Delete Floor
  As an admin
  I want to delete floor
  So that I can remove floor from database

  Background:
    Given I have logged in as admin
    And I get token admin from the response

  Scenario Outline: DELETE - As an admin I have to be able to delete floor
    Given I set an endpoint for DELETE floor
    When I request DELETE detail floor with "<statusAuthorize>" and input <floorId>
    Then I validate the status code for delete floor is <statusCode>
    And validate the "<message>" and data details after delete floor
    Examples:
      | statusAuthorize | floorId | statusCode | message      |
      | not authorized  | 0       | 403        | unauthorized |
      | authorized      | 0       | 400        | not found    |
      | authorized      | 99      | 400        | not found    |
      | authorized      | 43      | 200        | success      |
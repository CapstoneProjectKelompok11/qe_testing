Feature: Delete Favorite Building
  As a user
  I want to remove favorite of building
  So that I can remove favorite building

  Background:
    Given I have logged in as user
    And I get token from the response

  Scenario Outline: DELETE - As an admin I have to be able to remove favorite of building
    Given I set an endpoint for DELETE favorite
    When I request DELETE detail favorite with "<statusAuthorize>" and input <buildingId>
    Then I validate the status code for delete favorite is <statusCode>
    And validate the "<message>" and data details after delete favorite
    Examples:
      | statusAuthorize | buildingId | statusCode | message      |
      | not authorized  | 0          | 403        | unauthorized |
      | authorized      | 0          | 400        | not found    |
      | authorized      | 99         | 400        | not found    |
      | authorized      | 1          | 500        | bad request  |
      | authorized      | 7          | 200        | success      |
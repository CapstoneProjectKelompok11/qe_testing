Feature: Get Floor Image
  As an admin
  I want to get data image of floor
  So that I can see image of floor and access the image data floor

  Scenario Outline: GET - As an admin I have to be able to get data floor image
    Given I set an endpoint for GET floor image
    When I request GET detail floor image with input "<filename>"
    Then I validate the status code for get floor image is <statusCode>
    And validate the "<message>" and data floor image
    Examples:
      | filename | statusCode | message      |
      |          | 404        | not found    |
      | invalid  | 500        | error        |
      | valid    | 200        | success      |
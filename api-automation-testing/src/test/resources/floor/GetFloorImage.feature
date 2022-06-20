Feature: Floor
  As an admin
  I want to get data image of floor
  So that I can see image of floor and access the image data floor

  Scenario Outline: GET - As an admin I have to be able to get data image of floor
    Given I set an endpoint for GET image
    When I request GET detail image with input "<image>"
    Then I validate the status code for get image is <statusCode>
    And validate the "<message>" and data details
    Examples:
      | image   | statusCode | message      |
      |         | 404        | not found    |
      | invalid | 500        | error        |
      | valid   | 200        | success      |
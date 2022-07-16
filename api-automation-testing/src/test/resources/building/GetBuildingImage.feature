Feature: Get Building Image
  As an admin
  I want to get data image of building
  So that I can see image of building and access the image data building

  Scenario Outline: GET - As an admin I have to be able to get data building image
    Given I set an endpoint for GET building image
    When I request GET detail building image with input "<filename>"
    Then I validate the status code for get building image is <statusCode>
    And validate the "<message>" and data building image
    Examples:
      | filename | statusCode | message      |
      |          | 404        | not found    |
      | invalid  | 500        | error        |
      | valid    | 200        | success      |
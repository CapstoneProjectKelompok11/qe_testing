Feature: Get Profile Image
  As a user
  I want to get data image of profile
  So that I can see image of profile and access the image data profile

  Scenario Outline: GET - As a user I have to be able to get data profile image
    Given I set an endpoint for GET profile image
    When I request GET detail profile image with input "<filename>"
    Then I validate the status code for get profile image is <statusCode>
    And validate the "<message>" and data profile image
    Examples:
      | filename | statusCode | message      |
      |          | 404        | not found    |
      | invalid  | 500        | error        |
      | valid    | 200        | success      |
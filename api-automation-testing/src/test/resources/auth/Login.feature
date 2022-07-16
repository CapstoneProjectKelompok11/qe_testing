Feature: Login
  As an admin
  I want to login user
  So that I can access the detail data user

  Scenario Outline: POST - As an admin I have to be able to login user
    Given I set an endpoint for POST login
    When I request POST detail login with input "<email>" and "<password>"
    Then I validate the status code is <statusCode>
    And validate the data details and the "<message>" after login
    Examples:
      | email           | password  | statusCode | message  |
      |                 |           | 400        | required |
      |                 | user      | 400        | required |
      | existed         |           | 400        | required |
      | existed         | wrongPass | 400        | failed   |
      | not existed     | user      | 400        | failed   |
      | existed         | user      | 200        | success  |
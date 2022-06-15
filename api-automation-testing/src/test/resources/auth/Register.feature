Feature: Register
  As an admin
  I want to create new user
  So that I can access the detail data user

  Scenario Outline: POST - As an admin I have to be able to create new user
    Given I set an endpoint for POST register
    When I request POST detail register with input "<firstName>", "<lastName>", "<phone>", "<email>" and "<password>"
    Then I validate the status code is <statusCode>
    And validate the data details and the "<message>" after register
    Examples:
      | firstName | lastName | phone       | email   | password  | statusCode | message  |
      |           |          |             |         |           | 400        | required |
      | user      | name     | 08123456789 | existed |           | 400        | required |
      | user      | name     | 08123456789 |         | pass123*  | 400        | required |
      | user      | name     |             | existed | pass123*  | 400        | required |
      | user      |          | 08123456789 | existed | pass123*  | 400        | required |
      |           | name     | 08123456789 | existed | pass123*  | 400        | required |
      | user      | name     | 08123456789 | existed | pass123*  | 400        | existed  |
      | user      | name     | 08123456789 | new     | pass123*  | 200        | success  |
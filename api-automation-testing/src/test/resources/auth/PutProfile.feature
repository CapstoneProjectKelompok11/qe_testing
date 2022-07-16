Feature: Edit Data Profile
  As a user
  I want to edit data profile
  So that I can access the detail data user

  Background:
    Given I have logged in as user
    And I get token from the response

  Scenario Outline: PUT - As a user I have to be able to edit data profile
    Given I set an endpoint for PUT profile
    When I request PUT  detail profile with "<statusAuthorize>", input "<firstName>", "<lastName>", "<phone>", and "<email>"
    Then I validate the status code for put profile is <statusCode>
    And validate the data details and the "<message>" after edit profile
    Examples:
      | statusAuthorize | firstName | lastName | phone       | email   | statusCode | message      |
      | not authorized  |           |          |             |         | 403        | unauthorized |
      | authorized      |           |          |             |         | 400        | existed      |
      | authorized      | user      | name     | 08123456789 |         | 400        | required     |
      | authorized      | user      | name     |             | existed | 200        | success      |
      | authorized      | user      |          | 08123456789 | existed | 200        | success      |
      | authorized      |           | name     | 08123456789 | existed | 200        | success      |
      | authorized      | user      | name     | 08123456789 | invalid | 400        | required     |
      | authorized      | user      | name     | 08123456789 | new     | 200        | success      |
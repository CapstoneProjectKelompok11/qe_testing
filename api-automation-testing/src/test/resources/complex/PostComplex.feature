Feature: Create Complex
  As an admin
  I want to create data complex
  So that I can add data complex

  Background:
    Given I have logged in as admin
    And I get token admin from the response

  Scenario Outline: POST - As an admin I have to be able to crete data complex
    Given I set an endpoint for POST complex
    When I request POST detail complex with "<statusAuthorize>" and input "<complex>" and <cityId>
    Then I validate the status code for post complex is <statusCode>
    And validate the "<message>" and data details after post complex
    Examples:
      | statusAuthorize | complex            | cityId | statusCode | message      |
      | not authorized  |                    | 0      | 403        | unauthorized |
      | authorized      |                    | 0      | 400        | required     |
      | authorized      | Kino Office Tower  | 0      | 400        | required     |
#      | authorized      |                    | 3      | 400        | required     |
      | authorized      | Kino Office Tower  | 99     | 400        | not found    |
#      | authorized      | Kino Office Tower  | 6      | 200        | success      |
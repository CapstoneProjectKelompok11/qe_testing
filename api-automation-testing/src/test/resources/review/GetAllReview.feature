Feature: Get All Review
  As an admin
  I want to get data review
  So that I can see list of review of building

  Background:
    Given I have logged in as admin
    And I get token admin from the response

  Scenario Outline: GET - As an admin I have to be able to get data review
    Given I set an endpoint for GET all review
    When I request GET detail review with "<statusAuthorize>" and input "<token>"
    Then I validate the status code for get all review is <statusCode>
    And validate the "<message>" and all data details of review
    Examples:
      | statusAuthorize | token   | statusCode | message       |
      | not authorized  |         | 403        | unauthorized  |
#      | authorized      |         | 403        | unauthorized  |
      | authorized      | invalid | 403        | unauthorized  |
      | authorized      | valid   | 200        | success  |
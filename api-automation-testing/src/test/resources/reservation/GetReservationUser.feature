Feature: Get Reservation User
  As a user
  I want to get data reservation
  So that I can see list of reservation and access the detail data reservation

  Background:
    Given I have logged in as user
    And I get token from the response

  Scenario Outline: GET - As a user I have to be able to get data reservation
    Given I set an endpoint for GET reservation
    When I request GET detail reservation with "<statusAuthorize>" and "<token>"
    Then I validate the status code for get reservation is <statusCode>
    And validate the "<message>" and data details of reservation
    Examples:
      | statusAuthorize | token   | statusCode | message      |
#      | not authorized  |         | 403        | forbidden    |
#      | authorized      | null    | 403        | forbidden    |
#      | authorized      | invalid | 403        | forbidden    |
      | authorized      | valid   | 200        | success      |
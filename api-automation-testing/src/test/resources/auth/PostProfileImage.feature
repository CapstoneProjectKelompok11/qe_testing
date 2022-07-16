Feature: Create Profile Image
  As a user
  I want to create data profile image
  So that I can add data image of profile

  Background:
    Given I have logged in as user
    And I get token from the response

  Scenario Outline: POST - As a user I have to be able to create profile image
    Given I set an endpoint for POST profile image
    When I request POST detail profile image with "<statusAuthorize>" and input "<image>"
    Then I validate the status code for post profile image is <statusCode>
    And validate the "<message>" and data details after post profile image
    Examples:
      | statusAuthorize | image          | statusCode | message      |
      | not authorized  | valid format   | 403        | unauthorized |
      | authorized      |                | 400        | bad request  |
      | authorized      | invalid format | 400        | bad request  |
      | authorized      | valid format   | 200        | success      |
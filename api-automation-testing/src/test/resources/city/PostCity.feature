Feature: City
  As an admin
  I want to create data city
  So that I can add data city

  Background:
    Given I have logged in as admin
    And I get token admin for the request

  Scenario Outline: POST - As an admin I have to be able to create data city
    Given I set an endpoint for POST city
    When I request POST detail city with "<statusAuthorize>" and input "<city>"
    Then I validate the status code for post city is <statusCode>
    And validate the "<message>" and data details after post city
    Examples:
      | statusAuthorize | city  | statusCode | message      |
      | not authorized  |       | 403        | unauthorized |
      | authorized      |       | 200        | required     |
      | authorized      | Depok | 200        | success      |
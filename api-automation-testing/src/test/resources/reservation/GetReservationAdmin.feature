#Feature: Get All Reservation
#  As an admin
#  I want to get all data reservation
#  So that I can see list of reservation and access the detail data reservation
#
#  Background:
#    Given I have logged in as admin
#    And I get token admin from the response
#
#  Scenario Outline: GET - As an admin I have to be able to get data all reservation
#    Given I set an endpoint for GET all reservation
#    When I request GET detail all reservation with "<statusAuthorize>" and "<token>"
#    Then I validate the status code for get all reservation is <statusCode>
#    And validate the "<message>" and data details of all reservation
#    Examples:
#      | statusAuthorize | token   | statusCode | message      |
#      | not authorized  |         | 403        | forbidden    |
#      | authorized      | null    | 403        | forbidden    |
#      | authorized      | invalid | 403        | forbidden    |
#      | authorized      | valid   | 200        | success      |
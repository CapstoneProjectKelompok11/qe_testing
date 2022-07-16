#Feature: Get List User Chat
#  As a user
#  I want to get list user chat
#  So that I can see list of user chat
#
#  Background:
#    Given I have logged in as user
#    And I get token from the response
#
#  Scenario: GET - As a user I have to be able to get list user chat
#    Given I set an endpoint for GET chat
#    When I request GET detail chat
#    Then I validate the status code for get list chat is 200
#    And validate the data details of chat
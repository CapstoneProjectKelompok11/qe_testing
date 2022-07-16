#Feature: Sign In
#  As a user
#  I want to sign in account
#  So that I can access homepage
#
#  Background:
#    Given I am on welcome page
#    When I click sign in button
#
#  Scenario Outline: As a user, I have to be able to sign in
#    Given I am on login page
#    When I input "<email>" and "<password>"
#    And click login button
#    Then validate the "<message>" after login
#    Examples:
#      | email                | password  | message           |
#      |                      |           | required email    |
#      |                      | pass123#  | required email    |
#      | usermail@gmail.com   |           | required password |
#      | wrongemail@gmail.com | pass123#  | not existed       |
#      | usermail@gmail.com   | wrongpass | not existed       |
#      | usermail@gmail.com   | pass123#  | success           |
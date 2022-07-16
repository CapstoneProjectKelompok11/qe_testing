#Feature: Sign Up
#  As a user
#  I want to sign up account
#  So that I can sign in to app
#
#  Background:
#    Given I am on welcome page
#    When I click sign up button
#
#  Scenario Outline: As a user, I have to be able to sign up
#    Given I am on sign up page
#    When I input "<firstname>", "<lastname>", "<phone>", "<email>" and "<password>"
#    And click sign up button
#    Then validate the "<message>" after sign up
#    Examples:
#      | firstname | lastname | phone        | email          | password  | message            |
#      |           |          |              |                |           | required email     |
##      |           | name     | 081234567890 | existed        | pass123#  | required firstname |
##      | user      |          | 081234567890 | existed        | pass123#  | required lastname  |
#      | user      | name     |              | existed        | pass123#  | required phone     |
##      | user      | name     | 0812a38ja22i | existed        | pass123#  | invalid phone      |x
#      | user      | name     | 081234567890 |                | pass123#  | required email     |
##      | user      | name     | 081234567890 | existed        | pass123#  | existed email      |x
#      | user      | name     | 081234567890 | invalid format | pass123#  | invalid email      |
#      | user      | name     | 081234567890 | new            |           | required password  |
#      | user      | name     | 081234567890 | new            | pass      | invalid password   |
##      | user      | name     | 081234567890 | new            | pass123#  | success            |
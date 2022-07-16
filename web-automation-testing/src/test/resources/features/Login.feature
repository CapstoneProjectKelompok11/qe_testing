Feature: Login
  As a user
  I want to login
  So that I can access cart page

#  Background:
#    Given I am on product page
#    And I click user icon

  Scenario Outline: I have to be able to login
    Given I am on login page
    When I input "<email>" email
    And input "<password>" password on login page
    And click login button
    Then I get the "<result>" and "<text>" after login
    Examples:
      | email              | password | result             | text                 |
      |                    |          | icon warning       | email is required    |
      |                    | pass123* | icon warning       | email is required    |
      | user@gmail.com     |          | icon warning       | password is required |
      | same               | pass123# | icon warning       | record not found     |
      | new                | pass123# | icon warning       | record not found     |
      | same               | user     | direct to homepage |                      |
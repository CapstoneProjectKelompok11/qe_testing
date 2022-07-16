Feature: Register
  As a user
  I want to register / create a new account
  So that I can login

#  Background:
#    Given I am on product page
#    And I click login button
#    And click link register

  Scenario Outline: I have to be able to create new account
    Given I am on register page
    When I input "<firstName>" firstName
    And input "<lastName>" lastName
    And input "<phone>" phone
    And input "<email>" email
    And input "<password>" password
    And double click register button
    Then I get the "<result>" and "<message>" after register
    Examples:
      | firstName | lastName | phone        | email           | password | result       | message               |
      |           |          | 0            |                 |          | icon warning | fullname is required  |
      |           | name     | 081212938277 | user@gmail.com  | pass123* | icon warning | firstname is required |
      | user      |          | 081212938277 | same            | pass123* | icon warning | lastname is required  |
      | user      | name     | 0            | same            | pass123* | icon warning | phone is required     |
      | user      | name     | 081212938277 |                 | pass123* | icon warning | email is required     |
      | user      | name     | 081212938277 | same            | pass123* | icon warning | password is required  |
      | user      | name     | 081212938277 | same            |          | icon warning | email is required     |
      | user      | name     | abc          | same            | pass123* | icon warning | phone is invalid      |
      | user      | name     | 081212938277 | same            | pass123* | error        | email is exist        |
      | user      | name     | 081212938277 | email           | pass123* | icon warning | email is invalid      |
      | user      | name     | 081212938277 | same            | pass     | icon warning | password is invalid   |
      | user      | name     | 081212938277 | new             | pass123* | direct to login page |               |
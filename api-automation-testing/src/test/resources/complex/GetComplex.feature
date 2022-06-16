Feature: Complex
  As an admin
  I want to get data complex
  So that I can see list of complex and access the detail data complex

  Scenario Outline: GET - As an admin I have to be able to get data complex
    Given I set an endpoint for GET all complex with input "<city>"
    When I request GET detail complex with input "<city>"
    Then I validate the status code for get all complex is 200
    And validate the "<message>" and data details of complex
    Examples:
      | city          | message      |
      |               | get all data |
      | Jakarta       | not existed  |
      | Jakarta Pusat | existed      |
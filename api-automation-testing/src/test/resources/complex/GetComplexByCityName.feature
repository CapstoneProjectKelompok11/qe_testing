Feature: Get Complex with Input City Name
  As an admin
  I want to get data complex by input city name
  So that I can see list of complex and access the detail data complex

  Scenario Outline: GET - As an admin I have to be able to get data complex
    Given I set an endpoint for GET complex with input city
    When I request GET detail complex with input "<city>"
    Then I validate the status code for get complex is 200
    And validate the "<message>" and data details of complex
    Examples:
      | city         | message      |
      |              | not found    |
      | bekasi       | not existed  |
      | Bekasi       | existed      |
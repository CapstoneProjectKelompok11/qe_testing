#Feature: Get Payment Image
#  As an admin
#  I want to get data image of payment
#  So that I can see image of payment and access the image data payment
#
#  Scenario Outline: GET - As an admin I have to be able to get data payment image
#    Given I set an endpoint for GET payment image
#    When I request GET detail payment image with "<statusAuthorize>" and input "<filename>"
#    Then I validate the status code for get payment image is <statusCode>
#    And validate the "<message>" and data payment image
#    Examples:
#      | statusAuthorize | filename | statusCode | message      |
#      | not authorized  | valid    | 403        | unauthorized |
#      | authorized      | null     | 404        | not found    |
#      | authorized      | invalid  | 500        | not found    |
#      | authorized      | valid    | 200        | success      |
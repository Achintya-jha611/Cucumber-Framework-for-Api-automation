Feature: Validating place api with dynamic data
  Scenario Outline: Verify if place is being successfully added
    Given Add place valid payload is provided "<Name>" "<Place>"
    When User calls "AddPlaceApi" with post request method type
    Then api call status code happens to be 200
    And "status" in response body should be "OK"

    Examples:
      | Name     | Place  |
      | Achintya | Random |
      | Lovesh   | hisar  |
      | Vidit    | Noida  |



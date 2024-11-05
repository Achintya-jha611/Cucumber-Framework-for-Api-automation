Feature: Validating place api with dynamic data
  @AddPlace #defining tags
  Scenario Outline: Verify if place is being successfully added
    Given Add place valid payload is provided "<Name>" "<Place>"
    When User calls "AddPlaceApi" with "post" request method type
    Then api call status code happens to be 200
    And "status" in response body should be "OK"
    And verify place id created maps to "<Name>" using "getPlaceApi"

    Examples:
      | Name     | Place  |
      | Achintya | Random |
      | Lovesh   | hisar  |
     | Vidit    | Noida  |
   @DeletePlace
   Scenario: Verify if delete place Api working as expected
     Given Valid Delete place payload
     When User calls "deletePlaceAPI" with "post" request method type
     Then api call status code happens to be 200
     And "status" in response body should be "OK"

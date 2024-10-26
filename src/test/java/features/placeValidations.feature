Feature: Validating place api
  Scenario: Verify if place is being successfully added
    Given Add place valid payload
    When User calls "AddPlaceApi" with post request
    Then api call status code is 200
    And "status" in response body is "OK"


Feature: Validating Place Api
  @AddPlace
  Scenario Outline: Verify if place is being Successfully added using Add placeAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http Request
    Then The Api call got success with status code 200
    And  "status" in response body is "OK"
    And  "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"


    Examples:
    |name | language |address           |
    |atul | English  |World cross center|
  #  |akash|spanish   |trade cross  forum|

  @DeletePlace

  Scenario: Verify if delete place functionality is working
    Given DeleteplacePayload
    When user calls "deletePlaceAPI" with "Post" http Request
    Then The Api call got success with status code 200
    And  "status" in response body is "OK"



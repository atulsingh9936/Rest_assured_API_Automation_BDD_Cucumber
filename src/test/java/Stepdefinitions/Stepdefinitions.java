package Stepdefinitions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Add_place;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Stepdefinitions extends Utils

{
    RequestSpecification res;
    ResponseSpecification respec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;





    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        res=  given().spec(requestspecification())
                .body(data.addPlacePayload(name,language,address));


    }

    @When("user calls {string} with {string} http Request")
    public void userCallsWithHttpRequest(String resource, String method){
        // Write code here that turns the phrase above into concrete actions
        // constructor will be called  with value of resource what you pass

     APIResources resourceApi = APIResources.valueOf(resource);
        System.out.println(  resourceApi.getResource());
        respec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        if(method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceApi.getResource());
        else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceApi.getResource());

            
        }


    }

    @Then("The Api call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(response.getStatusCode(),200);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions


        assertEquals(getJsonPath(response,keyValue),ExpectedValue);
    }


    @Then("verify place_Id created maps to {string} using {string}")
    public void verifyPlaceIdCreatedMapsToUsing(String expected_name, String resource) throws IOException {
        // Requestspec
      place_id= getJsonPath(response,"place_id");
        res=  given().spec(requestspecification()).queryParam("place_id",place_id);
        userCallsWithHttpRequest(resource,"GET");
        String Actualname= getJsonPath(response,"name");
        assertEquals(Actualname,expected_name);



    }

    @Given("DeleteplacePayload")
    public void deleteplacePayload() throws IOException {
      res=  given().spec(requestspecification()).body(data.deletePlacePayload(place_id));
    }



}

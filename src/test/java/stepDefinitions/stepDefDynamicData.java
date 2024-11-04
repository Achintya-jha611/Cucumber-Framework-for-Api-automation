package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class stepDefDynamicData extends utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static String place_id;
    @Given("Add place valid payload is provided {string} {string}")
    public void add_place_valid_payload_with(String string1, String string2) throws IOException {
        TestDataBuild T=new TestDataBuild();
        //AddPlaceGoogleMaps a=T.addPlaceDynamically(string1,string2);
        res=given().spec(requestSpecBuilder()).body(T.addPlaceDynamically(string1,string2));
        //System.out.println(res);
    }
    @When("User calls {string} with {string} request method type")
    public void user_calls_with_post_request(String string,String httpMethod) {
        ApiResources resource=ApiResources.valueOf(string);//calling enum class to fetch values
        System.out.println(resource.getResource());
        resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(httpMethod.equalsIgnoreCase("post"))
            response = res.when().post(resource.getResource());
        else if (httpMethod.equalsIgnoreCase("get"))
            response = res.when().get(resource.getResource());


    }
    @Then("api call status code happens to be {int}")
    public void api_call_status_code_is(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body should be {string}")
    public void in_response_body_is(String key, String ExpectedValue) {
        assertEquals(JsonPathReader(this.response,key),ExpectedValue);
    }
    @Then("verify place id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
        place_id=JsonPathReader(response,"place_id");
        res=given().spec(requestSpecBuilder()).queryParam("place_id",place_id);
        user_calls_with_post_request(resource,"get");
        String nameSent=JsonPathReader(response,"name");
        assertEquals(ExpectedName,nameSent);
    }
    @Given("Valid Delete place payload")
    public void valid_delete_place_payload() throws IOException {
        TestDataBuild t1=new TestDataBuild();
       res=given().spec(requestSpecBuilder()).body(t1.getDeletePayload(place_id));
    }



}



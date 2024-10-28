package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.utils;
import test.AddPlaceGoogleMaps;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class stepDefDynamicData extends utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response resp;

    @Given("Add place valid payload is provided {string} {string}")
    public void add_place_valid_payload_with(String string1, String string2) throws IOException {
        TestDataBuild T=new TestDataBuild();
        //AddPlaceGoogleMaps a=T.addPlaceDynamically(string1,string2);
        System.out.println(string1);
        System.out.println(string2);
        res=given().spec(requestSpecBuilder()).body(T.addPlaceDynamically(string1,string2));

        System.out.println(res);
    }
    @When("User calls {string} with post request method type")
    public void user_calls_with_post_request(String string) {
        resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        resp= res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
        String respo= resp.asString();
        System.out.println(respo);
    }
    @Then("api call status code happens to be {int}")
    public void api_call_status_code_is(Integer int1) {
        assertEquals(resp.getStatusCode(),200);
    }
    @Then("{string} in response body should be {string}")
    public void in_response_body_is(String key, String ExpectedValue) {
        String response=resp.asString();
        JsonPath jp1=new JsonPath(response);
        assertEquals(jp1.get(key).toString(),ExpectedValue);
    }}

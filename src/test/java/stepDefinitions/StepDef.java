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
import resources.ApiResources;
import resources.TestDataBuild;
import resources.utils;
import pojo.AddPlaceGoogleMaps;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class StepDef extends utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response resp;

    @Given("Add place valid payload")
    public void add_place_valid_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        TestDataBuild T=new TestDataBuild();
        AddPlaceGoogleMaps a=T.addPlace();
        //RequestSpecification req=new RequestSpecBuilder().setBaseUri(baseURI).setContentType(ContentType.JSON).build();now we moved this line to utils file
        res=given().spec(requestSpecBuilder()).body(a);

        System.out.println(res);
    }
        @When("User calls {string} with {string} request")
        public void user_calls_with_post_request(String string,String httpMethod) {
            ApiResources resource=ApiResources.valueOf(string);//calling enum class to fetch values
            resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
            if(httpMethod.equalsIgnoreCase("post")){
                resp= res.when().post(resource.getResource());
            }
            else if (httpMethod.equalsIgnoreCase("get")){
                resp= res.when().get(resource.getResource());
            }

            String respo= resp.asString();
            System.out.println(respo);
        }
        @Then("api call status code is {int}")
        public void api_call_status_code_is(Integer int1) {
            assertEquals(resp.getStatusCode(),200);
        }
        @Then("{string} in response body is {string}")
        public void in_response_body_is(String key, String ExpectedValue) {
            String response=resp.asString();
            JsonPath jp1=new JsonPath(response);
            assertEquals(jp1.get(key).toString(),ExpectedValue);
    }


    }

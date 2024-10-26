package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.baseURI;

public class utils {
    public RequestSpecification requestSpecBuilder(){
        RequestSpecification req=new RequestSpecBuilder().setBaseUri(baseURI).setContentType(ContentType.JSON).build();
        return req;
    }
}

package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.baseURI;

public class utils {
    public RequestSpecification requestSpecBuilder() throws FileNotFoundException {
        PrintStream stream = new PrintStream(new FileOutputStream("logger.txt"));//Stream object of print stream class directs all the log generated to logger.txt file created at run time using Fileoutputstream object
        RequestSpecification req=new RequestSpecBuilder().setBaseUri(baseURI).setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream)).build();
        return req;
    }
}

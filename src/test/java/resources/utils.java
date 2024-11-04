package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class utils {
    public static RequestSpecification req;//if it would be static same instance would be used for all test cases in one execution
    public RequestSpecification requestSpecBuilder() throws IOException {

        //RestAssured.baseURI=utils.getGlobalKeys("baseUrl");

        if(req==null){
        PrintStream stream = new PrintStream(new FileOutputStream("logger.txt"));//Stream object of print stream class directs all the log generated to logger.txt file created at run time using Fileoutputstream object..setting it to true means append existing log file
        req=new RequestSpecBuilder().setBaseUri(getGlobalKeys("baseUrl")).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream)).build();
        return req;}
        return req;
    }
    public static String getGlobalKeys (String key) throws IOException {
        Properties pro = new Properties();//used to read a property file
        FileInputStream fis = new FileInputStream("/Users/achintyakaushaljha/Desktop/Cucumber/src/test/java/resources/global.properties");//reads a file and converts it to stream of bytes
        pro.load(fis);
        return pro.getProperty(key);//gets value corresponding to the key

    }
    public String JsonPathReader(Response response, String key){
        String resp=response.asString();
        JsonPath js=new JsonPath(resp);
        return js.get(key).toString();
    }
}

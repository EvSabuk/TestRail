package api.adapters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import utils.PropertyReader;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseApi {

    private static String getSystemVariable(String variableName) {
        return System.getProperty(variableName, PropertyReader.getProperty(variableName));
    }

    public static RequestSpecification getAuthSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(getSystemVariable("baseURL"))
                .setContentType("application/json")
                .setAuth(RestAssured
                        .preemptive()
                        .basic(getSystemVariable("email"), getSystemVariable("password")))
                .build();
    }

    public ResponseWrapper request(Method method, String endpoint) {
        return new ResponseWrapper(
                given()
                        .spec(getAuthSpec())
                        .request(method, endpoint)
                        .then()
                        .extract()
                        .response());
    }

    public ResponseWrapper requestBody(Method method, String endpoint, Object body) {
        return new ResponseWrapper(
                given()
                        .spec(getAuthSpec())
                        .body(body)
                        .request(method, endpoint)
                        .then()
                        .extract()
                        .response());
    }
}
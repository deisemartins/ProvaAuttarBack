package ProvaAuttarBack.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {
    @BeforeClass
    public static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }
}

package ProvaAuttarBack.tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.when;

public class DELETE {
    @BeforeClass
    public static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void delete () {

        when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}


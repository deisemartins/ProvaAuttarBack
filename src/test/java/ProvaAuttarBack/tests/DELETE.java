package ProvaAuttarBack.tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class DELETE extends BaseTest {

    private static final String LIST_USER = "/users";

    @Test
    public void delete () {
        given().
                params("id", "2").
        when()
                .delete(LIST_USER)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().all();

    }
}


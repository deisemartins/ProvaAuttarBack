package ProvaAuttarBack.tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class DELETE extends BaseTest {

    @Test
    public void delete () {

        when()
                .delete("/users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}


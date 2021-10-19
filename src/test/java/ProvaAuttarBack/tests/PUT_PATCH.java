package ProvaAuttarBack.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class PUT_PATCH extends BaseTest {

    @Test
    public void updatePut () {

       JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("name", "Morpheus");
        requestJsonObject.put("job", "Analista de negócio");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .put("/users/2")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void updatePatch () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("name", "Adalgisa");
        requestJsonObject.put("job", "Desenvolvedora");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .patch("/users/3")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}


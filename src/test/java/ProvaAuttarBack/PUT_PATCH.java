package ProvaAuttarBack;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PUT_PATCH {

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
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
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
                .patch("https://reqres.in/api/users/3")
                .then()
                .statusCode(200)
                .log().all();
    }
}


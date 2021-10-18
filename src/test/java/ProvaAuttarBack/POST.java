package ProvaAuttarBack;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class POST {

    @Test
    public void create () {

       JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("name", "João");
        requestJsonObject.put("job", "Analista");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("João"))
                .body("job", equalTo("Analista"));
    }

    @Test
    public void createNoNameField () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("job", "Analista");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("job", equalTo("Analista"))
                .log().all();
    }

    @Test
    public void createNoJobField () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("name", "Maria");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Maria"))
                .log().all();
    }

    @Test
    public void registerSucessful () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "eve.holt@reqres.in");
        requestJsonObject.put("password", "pistol");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200);
    }

    @Test
    public void registerUnsucessful () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "sydney@fife");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    public void loginSuccessful () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "eve.holt@reqres.in");
        requestJsonObject.put("password", "cityslicka");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void loginUnsuccessful () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "peter@klaven");
        System.out.println(requestJsonObject);

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}

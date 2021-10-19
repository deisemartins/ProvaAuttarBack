package ProvaAuttarBack.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import ProvaAuttarBack.support.user;

public class POST extends BaseTest {

    @Test
    public void create () {
        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("name", "João");
        requestJsonObject.put("job", "Analista");

        given().
                contentType(ContentType.JSON).
                body(requestJsonObject).
                when().
                post("/users").
                then().
                statusCode(HttpStatus.SC_CREATED).
                body("name", is("João")).
                body("job", is("Analista"));
    }

    @Test
    public void createNoNameField () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("job", "Analista");

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("job", equalTo("Analista"));
    }

    @Test
    public void createNoJobField () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("name", "Maria");

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("Maria"));
    }

    @Test
    public void registerSucessful () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "eve.holt@reqres.in");
        requestJsonObject.put("password", "pistol");

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("/register")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void registerUnsucessful () {
        user user = new user();
        user.setEmail("sydney@fife");

        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post("/register").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error", is("Missing password"));

    }

        /*JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "sydney@fife");

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("/register")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }*/

    @Test
    public void loginSuccessful () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "eve.holt@reqres.in");
        requestJsonObject.put("password", "cityslicka");

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("/login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void loginUnsuccessful () {

        JSONObject requestJsonObject = new JSONObject();

        requestJsonObject.put("email", "peter@klaven");

        given()
                .header("Content-Type", "Application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestJsonObject.toJSONString())
                .when()
                .post("/login")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }
}

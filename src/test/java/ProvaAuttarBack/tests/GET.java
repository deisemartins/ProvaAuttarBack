package ProvaAuttarBack.tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.PrimitiveIterator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GET extends BaseTest {
    private static final String LIST_USERS = "/users";
    private static final String LIST_USER = "/unknown";

    @Test
    public void listUser() {
        given()
                .get(LIST_USERS + "?page=2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data",is(notNullValue()))
                .body("data.id[0]", equalTo(7))
                .body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"));
    }

    @Test
    public void singleUser() {
        given()
                .get(LIST_USERS + "/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    public void singleUserNotFound() {
        given()
                .get(LIST_USERS + "/23")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        }

    @Test
    public void list() {
        given()
                .get(LIST_USER)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("page", equalTo(1))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2));
    }
    @Test
    public void singleList() {
        given()
                .get(LIST_USER + "/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.color", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"));
    }

    @Test
    public void singleListNotFound() {
        given()
                .get(LIST_USER+ "/23")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void delayedResponse() {
        given()
                .get(LIST_USERS +"?delay=3")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("page", equalTo(1))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data.id[0]", equalTo(1))
                .body("data.email[0]", equalTo("george.bluth@reqres.in"))
                .body("data.first_name[0]", equalTo("George"))
                .body("data.last_name[0]", equalTo("Bluth"))
                .body("data.avatar[0]", equalTo("https://reqres.in/img/faces/1-image.jpg"));
    }
}



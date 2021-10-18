package ProvaAuttarBack;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GET {

    @Test
    public void listUser() {
        given()
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7))
                .body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"))
                .log().all();
    }

    @Test
    public void singleUser() {
        given()
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
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
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
        }

    @Test
    public void list() {
        given()
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2));
    }
    @Test
    public void singleList() {
        given()
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.color", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"));
    }

    @Test
    public void singleListNotFound() {
        given()
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void delayedResponse() {
        given()
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data.id[0]", equalTo(1))
                .body("data.email[0]", equalTo("george.bluth@reqres.in"))
                .body("data.first_name[0]", equalTo("George"))
                .body("data.last_name[0]", equalTo("Bluth"))
                .body("data.avatar[0]", equalTo("https://reqres.in/img/faces/1-image.jpg"));
    }
}



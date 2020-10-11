package Tests;

import Utils.Util;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class User {

    String path = "src/test/resources/user.json";
    String jsonBody = Util.readJson(path);
    JSONObject jsonObj = new JSONObject(jsonBody);
    String result = Integer.toString(jsonObj.getInt("id"));

    @Test
    public void createUser() {
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
                .when()
                .post("https://petstore.swagger.io/v2/user")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(result))
        ;
    }

    @Test
    public void searchUser() {
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
                .when()
                .get("https://petstore.swagger.io/v2/user/KarinaOliveira")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(jsonObj.getInt("id")))
                .body("username", equalTo(jsonObj.getString("username")))
                .body("email", equalTo(jsonObj.getString("email")))
                .body("password", equalTo(jsonObj.getString("password")))
                .body("phone", equalTo(jsonObj.getString("phone")))
                .body("userStatus", equalTo(jsonObj.getInt("userStatus")))
        ;
    }
}

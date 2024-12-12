package tests;

import models.AuthorizedBodyModel;
import models.AuthorizedResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class AuthorizedTests extends BaseTest{

    @Test
    void successfulAuthorized() {

        AuthorizedBodyModel body = new AuthorizedBodyModel();
        body.setUserName("Ttest");
        body.setPassword("!Aa12345678");

    given()
        .body(body)
        .contentType(JSON)
        .log().uri()
    .when()
        .post("/Account/v1/Authorized")
    .then()
        .log().status()
        .log().body()
        .statusCode(200)
        .body("$", is(true));
    }

    @Test
    void unsuccessfulNotFoundUser404Authorized() {

        AuthorizedBodyModel body = new AuthorizedBodyModel();
        body.setUserName("Ttest1");
        body.setPassword("!Aa12345678");

    given()
        .body(body)
        .contentType(JSON)
        .log().uri()
    .when()
        .post("/Account/v1/Authorized")
    .then()
        .log().status()
        .log().body()
        .statusCode(404)
        .body("code", is("1207"))
        .body("message", is("User not found!"));
    }

    @Test
    void unsuccessfulEmptyBody400Authorized() {

    String data = "";

    AuthorizedResponseModel response = given()
        .body(data)
        .contentType(JSON)
        .log().uri()
    .when()
        .post("/Account/v1/Authorized")
    .then()
        .log().status()
        .log().body()
        .statusCode(400)
        .extract().as(AuthorizedResponseModel.class);

        Assertions.assertEquals("1200", response.getCode());
        Assertions.assertEquals("UserName and Password required.", response.getMessage());

    }

}

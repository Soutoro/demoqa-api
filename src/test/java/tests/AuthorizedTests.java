package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import models.AuthorizedBodyModel;
import models.AuthorizedResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class AuthorizedTests extends BaseTest{

    @Test
    void successfulAuthorized() {

        AuthorizedBodyModel body = new AuthorizedBodyModel(config.getLogin(), config.getPassword());

    given()
        .filter(new AllureRestAssured())
        .body(body)
        .contentType(JSON)
        .log().uri()
    .when()
        .post("/Account/v1/Authorized")
    .then()
        .statusCode(200)
        .body("$", is(true));
    }

    @Test
    void unsuccessfulNotFoundUser404Authorized() {

        AuthorizedBodyModel body = new AuthorizedBodyModel(config.getLogin(), config.getPassword());

    given()
        .filter(new AllureRestAssured())
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

    AuthorizedResponseModel response =  step("Отправить запрос", () -> given()
        .filter(new AllureRestAssured())
        .body(data)
        .contentType(JSON)
        .log().uri()
    .when()
        .post("/Account/v1/Authorized")
    .then()
        .log().status()
        .log().body()
        .statusCode(400)
        .extract().as(AuthorizedResponseModel.class));

    step("Проверить код и сообщение ответа", () -> {
        Assertions.assertEquals("1200", response.getCode());
        Assertions.assertEquals("UserName and Password required.", response.getMessage());
    });

    }

}

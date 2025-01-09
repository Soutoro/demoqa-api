package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.AuthorizedBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import steps.AddAndDeleteBookSteps;
import steps.AuthorizationSteps;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiUiTest extends BaseTest{

    AddAndDeleteBookSteps addAndDeleteBookSteps = new AddAndDeleteBookSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();

    @Test
    @DisplayName("Добавление книги через api и удаление через ui(старая версия)")
    public void addBookWithApiAndDeleteWithUi1() {
        AuthorizedBodyModel loginBody = new AuthorizedBodyModel(config.getLogin(), config.getPassword());

        Response authResponse = step("Авторизоваться через API", ()->given()
                .contentType(JSON)
                .body(loginBody)
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract().response());

        String userId = authResponse.path("userId");
        String body2 = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"9781449325862\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        given()
                .header("authorization", "Basic VHRlc3Q6IUFhMTIzNDU2Nzg=")
                .contentType(JSON)
                .body(body2)
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open("/profile");
        $x("//span[@id='delete-record-undefined']").click();
        $x("//button[@id='closeSmallModal-ok']").click();
        Selenide.confirm();
        $x("//div[text()='No rows found']").shouldBe(enabled);

        }

    @Test
    @DisplayName("Добавление книги в пустую таблицу через api и удаление через ui")
    public void addBookWithApiAndDeleteWithUi() {

        authorizationSteps.authorizationWithApi();
        addAndDeleteBookSteps.addBookWithApi();
        authorizationSteps.openPageWithCookie();
        addAndDeleteBookSteps.deleteBookWithUI();

    }

}

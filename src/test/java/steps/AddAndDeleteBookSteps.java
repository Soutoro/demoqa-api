package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
//import models.AddBookModel;
import models.AuthorizedBodyModel;
import models.SessionData;
import org.openqa.selenium.Cookie;
import pages.ProfilePage;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AddAndDeleteBookSteps extends BaseTest {

//    SessionData sessionData = new SessionData();
//    AuthorizedBodyModel loginBody = new AuthorizedBodyModel(config.getLogin(), config.getPassword());
    ProfilePage profilePage = new ProfilePage();

//    @Step("Авторизоваться через API")
//    public void authorizationWithApi() {
//
//        Response authResponse = given()
//                .body(loginBody)
//                .post("/Account/v1/Login")
//                .then()
//                .statusCode(200)
//                .extract().response();
//
//        sessionData.setUserID(authResponse.path("userId"));
//        sessionData.setExpires(authResponse.path("expires"));
//        sessionData.setToken(authResponse.path("token"));
//
//    }

//    @Step("Добавить книгу через API")
    public void addBookWithApi() {
        String body2 = "{\n" +
                "  \"userId\": \"" + "4e09fb4f-bbf0-4471-bf9d-81cc9996dd40" + "\",\n" +
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
    }


//    @Step("Удалить книгу через UI")
    public void deleteBookWithUI() {

//        open("/favicon.ico");
//        getWebDriver().manage().addCookie(new Cookie("userID", sessionData.getUserID()));
//        getWebDriver().manage().addCookie(new Cookie("expires", sessionData.getExpires()));
//        getWebDriver().manage().addCookie(new Cookie("token", sessionData.getToken()));

        open("/profile");
        profilePage.deleteBookButton().click();
        profilePage.confirmDeleteButton().click();
        Selenide.confirm();
        profilePage.messageAboutEmptyTable().shouldBe(enabled);
}


}

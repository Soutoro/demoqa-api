package tests;


import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class AllBooksTests extends BaseTest {

    @Test
    public void checkBook() {

        given()
                .filter(new AllureRestAssured())
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .body("books.size()", equalTo(8));

    }

    @Test
    public void checkBook1() {

        given()
                .filter(new AllureRestAssured())
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .body("books.find { it.isbn = '9781449325862'}.title", equalTo("Git Pocket Guide"));

    }

    @Test
    public void checkBook2() {

        given()
                .filter(new AllureRestAssured())
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .body("books.findAll {it.pages > 400}.pages.size()", equalTo(2));

    }

//    @Test
//    public void checkBook3() {
//
//        step("Тест", () -> {given()
//                .filter(new AllureRestAssured())
//                .when()
//                .get("/BookStore/v1/Books")
//                .then()
//                .body("books.size()", equalTo(8));});
//    }

}

package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    public SelenideElement deleteBookButton() {return $x("//span[@id='delete-record-undefined']");}
    public SelenideElement confirmDeleteButton() {return $x("//button[@id='closeSmallModal-ok']");}
    public SelenideElement messageAboutEmptyTable() {return $x("//div[text()='No rows found']");}
}

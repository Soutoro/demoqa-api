package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    public SelenideElement deleteBookButton() {return $x("//span[@id='delete-record-undefined']");}

}

package ui.pageobjects;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePageObject{
    private final SelenideElement usernameField = $("#form-username");
    private final SelenideElement passwordField = $("#form-password");
    private final SelenideElement submitButton = $x("/html/body/div/form/div[1]/button");


    public LoginPage openLoginPage() {
        open("");
        return new LoginPage();
    }
    public MainPage userLogin(String username, String password) {
        getUsernameField().shouldBe(visible).sendKeys(username);
        getPasswordField().sendKeys(password);
        getSubmitButton().click();
        return new MainPage();
    }
    public SelenideElement getUsernameField() {
        return usernameField;
    }

    public SelenideElement getPasswordField() {
        return passwordField;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }
}

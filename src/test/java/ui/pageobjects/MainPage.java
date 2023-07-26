package ui.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePageObject{

    private final SelenideElement newPersonalProjectLink = $x("//*[@id=\"main\"]/div/ul/li[1]/a");
    private final SelenideElement nameInput = $("#form-name");
    private final SelenideElement submitButton = $x("//*[@id=\"project-creation-form\"]/div[2]/div/button");
    private final SelenideElement projectName = $x("/html/body/header/div[1]/h1/span[2]");

    private final SelenideElement createProjectSuccess = $x("/html/body/section/div");


    public String createProject(String name){

        getNewPersonalProjectLink().click();
        getNameInput().shouldBe(visible).sendKeys(name);
        getSubmitButton().click();
        return getCreateProjectSuccess().shouldBe(visible).getText();
                //getProjectName().shouldBe(Condition.visible).getText();
    }
    public SelenideElement getNewPersonalProjectLink() {
        return newPersonalProjectLink;
    }

    public SelenideElement getNameInput() {
        return nameInput;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }
    public SelenideElement getProjectName() {
        return projectName;
    }

    public SelenideElement getCreateProjectSuccess() {
        return createProjectSuccess;
    }
}

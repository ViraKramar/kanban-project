package ui.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BoardPage {
    private final SelenideElement openDropdown = $x("//*[@id=\"main\"]/div[1]/div[1]/div/a/i[2]");
    private final SelenideElement selectCreateTask = $x("//*[@id=\"dropdown\"]/ul/li[1]/a");
    private final SelenideElement titleInput = $x("//*[@id=\"form-title\"]");

    private final SelenideElement referenceInput = $x("//*[@id=\"form-reference\"]");

    private final SelenideElement saveButton =
            $x("//*[@id=\"modal-content\"]/form/div/div[4]/div/div/button");
    private final SelenideElement successCreateTaskText = $x("/html/body/section/div[1]");

    public BoardPage openBoardPage(String project_id) {
        open("http://localhost/project/" + project_id);
        return new BoardPage();
    }

    public String createTask(String title, String reference){

        getOpenDropdown().shouldBe(visible).click();
        getSelectCreateTask().shouldBe(visible).click();
        getTitleInput().shouldBe(visible).sendKeys(title);
        getReferenceInput().shouldBe(visible).sendKeys(reference);
        getSaveButton().shouldBe(visible).click();
        return getSuccessCreateTaskText().shouldBe(visible).getText();
    }

    public SelenideElement getOpenDropdown() {
        return openDropdown;
    }

    public SelenideElement getSelectCreateTask() {
        return selectCreateTask;
    }

    public SelenideElement getTitleInput() {
        return titleInput;
    }

    public SelenideElement getReferenceInput() {
        return referenceInput;
    }

    public SelenideElement getSaveButton() {
        return saveButton;
    }

    public SelenideElement getSuccessCreateTaskText() {
        return successCreateTaskText;
    }
}

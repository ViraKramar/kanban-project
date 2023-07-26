package ui.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TaskObject {
    private final SelenideElement openDropdownForTask =
            $x("//*[@id=\"main\"]/div/div[3]/div/form/div/div[1]/div/a/i[2]");
    private final SelenideElement selectMyTasks = $x("//*[@id=\"dropdown\"]/ul/li[2]/a");
    private final SelenideElement openActionDropdown =
            $x("/html/body/section/div[2]/div[2]/div[1]/div/a/strong/i");
    private final SelenideElement selectCloseTaskAction = $x("//*[@id=\"dropdown\"]/ul/li[14]/a");
    private final SelenideElement confirmCloseTaskAction = $x("//*[@id=\"modal-confirm-button\"]");

    private final SelenideElement closedTaskMessage = $x("/html/body/section/p");
    private final SelenideElement successCloseTaskText = $x("/html/body/section/div[1]");

    private final SelenideElement newTaskActionDropdown =
            $x("/html/body/section/div[2]/div[2]/div[1]/div/a/strong");
    private final SelenideElement selectAddCommentAction = $x("//*[@id=\"dropdown\"]/ul/li[6]/a");
    private final SelenideElement commentInput = $x("//*[@id=\"modal-content\"]/form/div[1]/div/div[2]/textarea");
    private final SelenideElement confirmComment = $x("//*[@id=\"modal-content\"]/form/div[2]/div/button");
    private final SelenideElement successText = $("body > section > div.alert.alert-success.alert-fade-out");
    public String closeTask(){

        getOpenDropdownForTask().shouldBe(visible).click();
        getSelectMyTasks().shouldBe(visible).click();
        getOpenActionDropdown().shouldBe(visible).click();
        getSelectCloseTaskAction().shouldBe(visible).click();
        getConfirmCloseTaskAction().shouldBe(visible).click();
        return getSuccessCloseTaskText().shouldBe(visible).getText();
    }

    public String createComment(Integer task_id, Integer user_id, String content){

        getOpenDropdownForTask().shouldBe(visible).click();
        getSelectMyTasks().shouldBe(visible).click();
        getOpenActionDropdown().shouldBe(visible).click();
        getSelectAddCommentAction().shouldBe(visible).click();
        getCommentInput().shouldBe(visible).sendKeys(content);
        getConfirmComment().shouldBe(visible).click();
        return getSuccessText().shouldBe(visible).getText();

    }

    public SelenideElement getOpenDropdownForTask() {
        return openDropdownForTask;
    }

    public SelenideElement getSelectMyTasks() {
        return selectMyTasks;
    }

    public SelenideElement getOpenActionDropdown() {
        return openActionDropdown;
    }

    public SelenideElement getSelectCloseTaskAction() {
        return selectCloseTaskAction;
    }

    public SelenideElement getConfirmCloseTaskAction() {
        return confirmCloseTaskAction;
    }

    public SelenideElement getClosedTaskMessage() {
        return closedTaskMessage;
    }

    public SelenideElement getSuccessCloseTaskText() {
        return successCloseTaskText;
    }

    public SelenideElement getSelectAddCommentAction() {
        return selectAddCommentAction;
    }

    public SelenideElement getCommentInput() {
        return commentInput;
    }

    public SelenideElement getConfirmComment() {
        return confirmComment;
    }

    public SelenideElement getSuccessText() {
        return successText;
    }
    public SelenideElement getNewTaskActionDropdown() {
        return newTaskActionDropdown;
    }

}

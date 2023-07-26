package ui.tests;

import api.steps.ProjectActionsSteps;
import api.steps.TaskActionsStep;
import api.steps.UserActionsSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageobjects.BoardPage;
import ui.pageobjects.LoginPage;
import ui.pageobjects.MainPage;
import ui.pageobjects.TaskObject;

public class CreateCommentTest extends BaseTest {
    UserActionsSteps userActionsSteps = new UserActionsSteps();
    LoginPage loginPage = new LoginPage();
    ProjectActionsSteps projectActionsSteps = new ProjectActionsSteps();
    TaskActionsStep taskActionsStep = new TaskActionsStep();

    private Integer userId;


    @BeforeMethod
    public void createUserLoginProjectTask(){
        String newId = userActionsSteps.creationUser(getUserName(), getName(), getPassword(), getRole());
        userId = Integer.valueOf(newId);
        loginPage.openLoginPage();
        loginPage.userLogin(getUserName(), getPassword());
        System.out.println(userId);
    }

    @Test
    @Description("Testing create comment: positive")
    public void createCommentUI() {

        String testProjectName = "Test Project UI777";
        String testTaskTitle = "The New Task Title77";
        String testReference = "R77";
        new MainPage()
                .createProject(testProjectName);
        Integer projectId = projectActionsSteps.getProjectIdByName(testProjectName);
        new BoardPage()
                .createTask(testTaskTitle, testReference);
        Integer taskId = Integer.valueOf(taskActionsStep.getTaskIdByReference(projectId, "R77"));
        String commentText = new TaskObject()
                .createComment(taskId,userId,"I love Java");
        Assert.assertEquals(commentText, "Comment added successfully.", "Create comment function works wrong");
        taskActionsStep.removeTask(taskId);
        projectActionsSteps.removeProject(projectId);
    }

    @AfterMethod(alwaysRun = true)
    public void removeTestUser(){
        userActionsSteps.removeUser(userId);
    }
}

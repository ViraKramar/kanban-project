package ui.tests;

import api.steps.ProjectActionsSteps;
import api.steps.TaskActionsStep;
import api.steps.UserActionsSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageobjects.*;

public class CloseTaskTest extends BaseTest{
    UserActionsSteps userActionsSteps = new UserActionsSteps();
    LoginPage loginPage = new LoginPage();
    ProjectActionsSteps projectActionsSteps = new ProjectActionsSteps();
    TaskActionsStep taskActionsStep = new TaskActionsStep();

    private Integer userId;
    private Integer projectId;
    private Integer taskId;

    @BeforeMethod
    public void createUserLogin(){
        String newId = userActionsSteps.creationUser(getUserName(), getName(), getPassword(), getRole());
        userId = Integer.valueOf(newId);
        loginPage.openLoginPage();
        loginPage.userLogin(getUserName(), getPassword());
        System.out.println(userId);
        System.out.println(projectId);
    }

    @Test
    @Description("Testing create task: positive")
    public void closeTaskUI() {
        String testProjectName = "Test Project UI 111";
        String testTaskTitle = "The New Task Title 3";
        String testReference = "R22";
        new MainPage()
                .createProject(testProjectName);
        projectId = projectActionsSteps.getProjectIdByName(testProjectName);
        new BoardPage()
                .createTask(testTaskTitle, testReference);
        Integer taskId = Integer.valueOf(taskActionsStep.getTaskIdByReference(projectId, "R22"));
        String taskStatus = new TaskObject()
                .closeTask();
        Assert.assertEquals(taskStatus, "Task closed successfully.", "Close task function works wrong");
        Boolean removingResult = taskActionsStep.removeTask(taskId);
        System.out.println(removingResult);
        projectActionsSteps.removeProject(projectId);
    }

    @AfterMethod(alwaysRun = true)
    public void removeTestUser(){
        userActionsSteps.removeUser(userId);
    }

}

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


public class CreateTaskTest extends BaseTest {
    UserActionsSteps userActionsSteps = new UserActionsSteps();
    LoginPage loginPage = new LoginPage();
    ProjectActionsSteps projectActionsSteps = new ProjectActionsSteps();
    TaskActionsStep taskActionsStep = new TaskActionsStep();

    private Integer userId;
    private Integer projectId;

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
    public void createTaskUI() {

       String testProjectName = "Test Project UI555";
        String testTaskTitle = "The New Task Title";
        String testReference = "R11";
        new MainPage()
                .createProject(testProjectName);
        projectId = projectActionsSteps.getProjectIdByName(testProjectName);
        String taskSuccess = new BoardPage()
                .createTask(testTaskTitle, testReference);
        Integer taskId = Integer.valueOf(taskActionsStep.getTaskIdByReference(projectId, "R11"));
        Assert.assertNotNull(taskId, "Create task function works wrong");
        projectActionsSteps.removeProject(projectId);
    }
    @AfterMethod(alwaysRun = true)
    public void removeTestUser(){
        userActionsSteps.removeUser(userId);
    }

}

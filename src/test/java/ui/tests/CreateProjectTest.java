package ui.tests;

import api.steps.ProjectActionsSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageobjects.BasePageObject;
import ui.pageobjects.LoginPage;
import api.steps.UserActionsSteps;
import ui.pageobjects.MainPage;

public class CreateProjectTest extends BaseTest {

    UserActionsSteps userActionsSteps = new UserActionsSteps();
    LoginPage loginPage = new LoginPage();
    ProjectActionsSteps projectActionsSteps = new ProjectActionsSteps();
    private Integer userId;

    @BeforeMethod
    public void createUserAndLogin(){
        String newId = userActionsSteps.creationUser(getUserName(), getName(), getPassword(), getRole());
        userId = Integer.valueOf(newId);
        loginPage.openLoginPage();
        loginPage.userLogin(getUserName(), getPassword());
        System.out.println(userId);
    }

    @Test
    @Description("Testing create project: positive")
    public void createProjectUI() {
       String newNumber = new BasePageObject()
                .generateRandom();
        String testProjectName = "Test Project UI " + newNumber;
        String successMessage = new MainPage()
                .createProject(testProjectName);

        Assert.assertEquals(successMessage, "Your project has been created successfully.", "Project creation works wrong");
        Integer projectId = projectActionsSteps.getProjectIdByName(testProjectName);
        System.out.println(projectId);
        projectActionsSteps.removeProject(projectId);
    }

    @AfterMethod(alwaysRun = true)
    public void removeTestUser(){
        userActionsSteps.removeUser(userId);
    }

}

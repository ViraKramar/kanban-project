package ui.tests;

import api.steps.UserActionsSteps;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageobjects.LoginPage;

public class LoginTest extends BaseTest {

    UserActionsSteps userActionsSteps = new UserActionsSteps();

    private Integer userId;
    @BeforeMethod
    public void createTestUser(){
        String newId = userActionsSteps.creationUser(getUserName(), getName(), getPassword(), getRole());
        userId = Integer.valueOf(newId);
        System.out.println(userId);
    }

    @Test
    @Description("Testing user login: positive")
    public void loginByNewUser() {
        new LoginPage()
                .openLoginPage()
                .userLogin(getUserName(), getPassword());
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), getMainURL(), "The logIn was not successful");
    }

    @AfterMethod(alwaysRun = true)
    public void removeTestUser(){
        userActionsSteps.removeUser(userId);
    }
}


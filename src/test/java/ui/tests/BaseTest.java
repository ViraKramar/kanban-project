package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.pageobjects.BasePageObject;


public class BaseTest {

    String newNumber = new BasePageObject()
            .generateRandom();

    private final String userName = "myTestUser" + newNumber;

    private final String name = "myTest";
    private final String password = "myTestPassword" + newNumber;
    private final String role = "app-user";
    private final String mainURL = "http://localhost/";
    private final String URL = "http://localhost:80";


    @BeforeMethod
    public void setUp() {
//        Configuration.browser = "chrome";
        Configuration.browser = "firefox";
        Configuration.baseUrl = URL;
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getMainURL() {
        return mainURL;
    }

    public String getURL() {
        return URL;
    }
}
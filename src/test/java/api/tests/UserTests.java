package api.tests;

import api.models.*;
import io.restassured.RestAssured;
import api.steps.UserActionsSteps;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests {
    @Test(description = "Testing User Details receiving by id through API: positive")
    public void getUserDetailsById(){
        UserActionsSteps userActionsSteps = new UserActionsSteps();
        String userName = userActionsSteps.getUserDetails(3);
        System.out.println(userName);
       //Integer userId = Integer.valueOf(response.as(GetResponse.class).getResulting().getId());
       Assert.assertEquals(userName, "onemore", "Get user details option works wrong");

    }
    @Test(description = "Testing User Details receiving by id through API if user unauthorized: negative")
    public void getUserDetailsUnauthorizedError(){
        UserActionsSteps userActionsSteps = new UserActionsSteps();
        Integer code = userActionsSteps.getUserDetailsWrongAuth(3);
        Assert.assertEquals(code, 401, "Unauthorized user flow works wrong");
    }
    @Test(description = "Testing User Creation through API: positive")
    public void createUserTest(){
        UserActionsSteps userActions = new UserActionsSteps();
        String userId = userActions.creationUser("MyNewUser","New1","123456", "app-user");
        System.out.println(userId);
        Integer newId = Integer.valueOf(userId);
        String userName = userActions.getUserDetails(newId);
       Assert.assertEquals(userName, "MyNewUser", "User creation works wrong");
       userActions.removeUser(newId);
    }

    @Test(description = "Testing User Creating through API with non-unique username: negative")
    public void createUserWrongUsernameTest() {
        UserActionsSteps userActionsSteps = new UserActionsSteps();
        Boolean response = userActionsSteps.createUserWrongParams("onemore","Mike", "123456", "app-user");
        Assert.assertEquals(response, false, "Create user option works wrong for non-unique username");
    }
    @Test(description = "Testing User Creating through API with wrong params: negative")
    public void createUserEmptyUsername() {
        UserActionsSteps userActionsSteps = new UserActionsSteps();
        Boolean response = userActionsSteps.createUserWrongParams("","Mike", "123456", "app-user");
        Assert.assertEquals(response, false, "Create user option works wrong for empty username field");
    }
    @Test(description = "Testing User Removing through API: positive")
    public void removeUserTest(){
        UserActionsSteps userActionsSteps = new UserActionsSteps();
        String userId = userActionsSteps.creationUser("removing1", "Remove", "123456", "app-user");
        Integer newUserId = Integer.valueOf(userId);
        Boolean resulting = userActionsSteps.removeUser(newUserId);
        Assert.assertEquals(resulting, true, "Remove user option works wrong");
    }
    @Test(description = "Testing User Removing through API with wrong params: negative")
    public void removeUserWrongParamsTest(){
        UserActionsSteps userActionsSteps = new UserActionsSteps();
        Boolean result = userActionsSteps.removeUser(null);
        Assert.assertEquals(result, false, "Remove user option works wrong for userId is null");
    }

}

package api.steps;
import api.models.*;
import api.models.errors.UnauthorizedResponse;
import api.models.user.CreateUserBody;
import api.models.user.GetUserBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class UserActionsSteps extends BaseSteps {

    public String getUserDetails(Integer userId){
        GetUserBody getUserBody = GetUserBody.builder()
                .user_id(userId)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getUserBody)
                .method("getUser")
                .build();

        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);
        response.prettyPrint();
        return response.as(GetResponse.class).getResult().getUsername();
    }
    public Integer getUserDetailsWrongAuth(Integer userId){
        GetUserBody getUserBody = GetUserBody.builder()
                .user_id(userId)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getUserBody)
                .method("getUser")
                .build();

        Response response = RestAssured.given().log().all()
                .auth().basic("", getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(401);
        response.prettyPrint();
        return response.as(UnauthorizedResponse.class).getError().getCode();
    }
    public boolean removeUser(Integer userId){
        GetUserBody getUserBody = GetUserBody.builder()
                .user_id(userId)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getUserBody)
                .method("removeUser")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);
        return (boolean) response.as(Resulting.class).getResult();
    }
    public String creationUser(String username, String name, String pass, String role){
        CreateUserBody body = CreateUserBody.builder()
                .username(username)
                .name(name)
                .password(pass)
                .email(username + "@gmail.co")
                .role(role)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(body)
                .method("createUser")
                .build();

        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        Resulting result = response.as(Resulting.class);
        response.then().statusCode(200);
        return result.getResult().toString();
    }
    public boolean createUserWrongParams(String username, String name, String pass, String role){
        CreateUserBody body = CreateUserBody.builder()
                .username(username)
                .name(name)
                .password(pass)
                .email(username + "@gmail.co")
                .role(role)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder().
                params(body)
                .method("createUser")
                .build();

        Response response = RestAssured.given()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);

        return (boolean) response.as(Resulting.class).getResult();
    }

}


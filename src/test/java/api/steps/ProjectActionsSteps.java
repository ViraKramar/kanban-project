package api.steps;

import api.models.*;
import api.models.project.CreateProjectBody;
import api.models.project.GetProjectBody;
import api.models.project.GetProjectByName;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProjectActionsSteps extends BaseSteps{

    public Integer createProject(String name, String description) {

    CreateProjectBody body = CreateProjectBody.builder()
            .name(name)
            .description(description)
            .build();
    BodyArgs bodyArgs = BodyArgs.builder()
            .params(body)
            .method("createProject")
            .build();
    Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        Resulting res = response.as(Resulting.class);
        response.then().statusCode(200);
        return (Integer) res.getResult();
    }
    public String getProjectDetails(Integer projectId){
        GetProjectBody getProjectBody = GetProjectBody.builder()
                .project_id(projectId)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getProjectBody)
                .method("getProjectById")
                .build();

        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());

        response.then().statusCode(200);
        response.prettyPrint();
        return response.as(GetResponse.class).getResult().getName();
    }

    public Integer getProjectIdByName(String name){
        GetProjectByName getProjectByName = GetProjectByName.builder()
                .name(name)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getProjectByName)
                .method("getProjectByName")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);
        response.prettyPrint();
        return Integer.valueOf(response.as(GetResponse.class).getResult().getId());
    }

    public boolean removeProject(Integer projectId){
        GetProjectBody getProjectBody = GetProjectBody.builder()
                .project_id(projectId)
                .build();

                BodyArgs bodyArgs = BodyArgs.builder()
                .params(getProjectBody)
                .method("removeProject")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);
        return (boolean) response.as(Resulting.class).getResult();
    }
}


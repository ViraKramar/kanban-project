package api.steps;

import api.models.BodyArgs;
import api.models.GetResponse;
import api.models.Resulting;
import api.models.project.GetProjectBody;
import api.models.task.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class TaskActionsStep extends BaseSteps{
    public Integer createTask(String title, Integer project_id, String color_id, String reference){
        CreateTaskBody body = CreateTaskBody.builder()
                .title(title)
                .project_id(project_id)
                .color_id(color_id)
                .reference(reference)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(body)
                .method("createTask")
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
    public String getTaskTitle(Integer task_id){
        GetTaskBody getTaskBody = GetTaskBody.builder()
                .task_id(task_id)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getTaskBody)
                .method("getTask")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());

        response.then().statusCode(200);
        return response.as(GetResponse.class).getResult().getTitle();
    }
    public String getTaskIdByReference(Integer project_id, String reference){

        GetTaskByReference getTaskByReference = GetTaskByReference.builder()
                .project_id(project_id)
                .reference(reference)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getTaskByReference)
                .method("getTaskByReference")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());

        response.then().statusCode(200);
        return response.as(GetResponse.class).getResult().getId();
    }

    public Boolean closeTask(Integer task_id){

        GetTaskBody getTaskBody = GetTaskBody.builder()
                .task_id(task_id)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getTaskBody)
                .method("closeTask")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());

        response.then().statusCode(200);
        response.prettyPrint();
        return (boolean) response.as(Resulting.class).getResult();
    }

    public boolean removeTask(Integer task_id){
        GetTaskBody getTaskBody = GetTaskBody.builder()
                .task_id(task_id)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(getTaskBody)
                .method("removeTask")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);
        return (boolean) response.as(Resulting.class).getResult();
    }
    public Integer createComment(Integer user_id, Integer task_id, String content){
        CreateCommentBody body = CreateCommentBody.builder()
                .user_id(user_id)
                .task_id(task_id)
                .content(content)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(body)
                .method("createComment")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);
        return (Integer) response.as(Resulting.class).getResult();
    }
    public String getComment(Integer comment_id){
        GetComment body = GetComment.builder()
                .comment_id(comment_id)
                .build();
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(body)
                .method("getComment")
                .build();
        Response response = RestAssured.given().log().all()
                .auth().basic(getJsonrpc(), getToken())
                .body(bodyArgs)
                .when()
                .post(getURL());
        response.then().statusCode(200);
        return (String) response.as(GetResponse.class).getResult().getComment();
    }

    public boolean removeComment(Integer comment_id){
        GetComment body = GetComment.builder()
                .comment_id(comment_id)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(body)
                .method("removeComment")
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

package api.tests;

import api.steps.BaseSteps;
import api.steps.ProjectActionsSteps;
import api.steps.TaskActionsStep;
import api.steps.UserActionsSteps;
import org.testng.Assert;
import org.testng.IInjectorFactory;
import org.testng.annotations.Test;
import ui.pageobjects.BasePageObject;

public class ProjectTests {
    @Test(description = "Testing Project Creation through API: positive")
    public void createProjectTest(){
        ProjectActionsSteps projectActionsSteps = new ProjectActionsSteps();
        Integer projectId = projectActionsSteps.createProject("Alexa Project", "Some new project");
        System.out.println(projectId);
        String projectName = projectActionsSteps.getProjectDetails(projectId);
        Assert.assertEquals(projectName, "Alexa Project", "Project creation works wrong");
        projectActionsSteps.removeProject(projectId);
    }

    @Test(description = "Testing Project Deleting through API: positive")
    public void removeProjectTest(){
        ProjectActionsSteps projectActionsSteps = new ProjectActionsSteps();
        Integer projectId = projectActionsSteps.createProject("Removing Project", "Remove text project");
        System.out.println(projectId);
        Boolean result = projectActionsSteps.removeProject(projectId);
        Assert.assertEquals(result, true, "Project deleting works wrong");

    }

    @Test(description = "Testing Task Creating through API: positive")
    public void createTaskTest(){
        TaskActionsStep taskActionsStep = new TaskActionsStep();
        Integer taskId = taskActionsStep.createTask("Task Test",10,"red", "The Second Task");
        String title = taskActionsStep.getTaskTitle(taskId);
        Assert.assertEquals(title, "Task Test", "Create task function works wrong");
        taskActionsStep.removeTask(taskId);
    }

    @Test(description = "Testing Task Removing through API: positive")
    public void removeTaskTest(){
        TaskActionsStep taskActionsStep = new TaskActionsStep();
        Integer taskId = taskActionsStep.createTask("Task Test",10,"green", "The Removing Task Test");
        Boolean removingResult = taskActionsStep.removeTask(taskId);
        Assert.assertEquals(removingResult, true, "Remove task function works wrong");
    }

    @Test(description = "Testing Getting Task Id by reference through API: positive")
    public void getTaskIdByReference(){
        TaskActionsStep taskActionsStep = new TaskActionsStep();
        Integer newId = Integer.valueOf(taskActionsStep.getTaskIdByReference(10, "The First Task"));
        Assert.assertEquals(newId,1, "Getting task id by reference works wrong");
        System.out.println(newId);
    }
    @Test(description = "Testing task comment creation")
    public void createComment(){
        TaskActionsStep taskActionsStep = new TaskActionsStep();
        Integer commentId = taskActionsStep.createComment(3, 11, "New comment #1000");
        System.out.println(commentId);
        String commentResult = taskActionsStep.getComment(commentId);
        Assert.assertEquals(commentResult, "New comment #1000", "Create comment function works wrong");
        taskActionsStep.removeComment(commentId);
    }

}

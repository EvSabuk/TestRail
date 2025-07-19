package tests.api;

import api.adapters.BaseApi;
import api.adapters.ResponseWrapper;
import api.dto.Project;
import api.dto.ProjectFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.restassured.http.Method;
import org.testng.annotations.Test;
import tests.ui.BaseTest;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CRUDProjectTest extends BaseTest {
    public static final String BASE_URI = "index.php?/api/v2/",
            ADD_PROJECT_URI = "add_project/",
            UPDATE_PROJECT_URI = "update_project/",
            ALL_PROJECTS_URI = "get_projects/";

    @Test(testName = "Check that it is possible to create a new Project via API.", priority = 1, groups = {"smoke"})
    @Epic("API Project")
    @Owner("Evgeny Sabuk")
    @Description("Check Project creation.")
    public void createNewProjectTest() {
        Project project = new ProjectFactory().newProject();
        ResponseWrapper response = new BaseApi()
                .requestBody(Method.POST, BASE_URI + ADD_PROJECT_URI, project);
        response.haveStatusCode(200);
        String name = response.getResponse().jsonPath().getString("name");
        assertEquals(name, project.getName(), "Names are not equal");
    }

    @Test(testName = "Check that it is possible to read an existing Project via API.", priority = 2, groups = {"mat"})
    @Epic("API Project")
    @Owner("Evgeny Sabuk")
    @Description("Check Project reading.")
    public void readNewProjectTest() {
        Project project = new ProjectFactory().newProject();
        ResponseWrapper response = new BaseApi()
                .requestBody(Method.POST, BASE_URI + ADD_PROJECT_URI, project);
        String name = response.getResponse().jsonPath().getString("name");
        response.haveStatusCode(200);
        response.haveJsonField("name", name);
    }

    @Test(testName = "Check that it is possible to edit an existing Project via API.", priority = 2, groups = {"mat"})
    @Epic("API Project")
    @Owner("Evgeny Sabuk")
    @Description("Check Project editing.")
    public void updateExistingProjectTest() {
        Project project = new ProjectFactory().newProject();
        ResponseWrapper getFirstProject = new BaseApi()
                .request(Method.GET, BASE_URI + ALL_PROJECTS_URI);
        String projectId = getFirstProject.getResponse().jsonPath().getString("projects[0].id");
        ResponseWrapper updateProject = new BaseApi()
                .requestBody(Method.POST, BASE_URI + UPDATE_PROJECT_URI + projectId, project);
        updateProject.haveStatusCode(200);
        String afterName = updateProject.getResponse().jsonPath().getString("name");
        assertEquals(afterName, project.getName(), "Names are not equal");
    }

    @Test(testName = "Check that it is possible to delete an existing Project via API.", priority = 2, groups = {"smoke"})
    @Epic("API Project")
    @Owner("Evgeny Sabuk")
    @Description("Check Project deletion.")
    public void deleteProjectByName() {
        ResponseWrapper getFirstProject = new BaseApi()
                .request(Method.GET, BASE_URI + ALL_PROJECTS_URI);
        String projectName = getFirstProject.getResponse().jsonPath().getString("projects[0].name");
        projectAPIStep.deleteProjectAPI(projectName);
        ResponseWrapper listOfProjects = new BaseApi()
                .request(Method.GET, BASE_URI + ALL_PROJECTS_URI);
        List<String> projectNames = listOfProjects.getResponse().jsonPath().getList("projects.name");
        softAssert.assertEquals(getFirstProject.getStatusCode(), 200, "Expected 200 after deletion");
        softAssert.assertFalse(projectNames.contains(projectName), "Project is not deleted");
        softAssert.assertAll();
    }
}
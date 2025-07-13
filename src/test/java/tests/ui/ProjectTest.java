package tests.ui;

import dto.Project;
import dto.ProjectFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {

    @Test(testName = "Check that it is possible to create a new Project.", priority = 1, groups = {"smoke"})
    @Epic("Project")
    @Owner("Evgeny Sabuk")
    @Description("Check Project creation.")
    public void checkCreateProjectTest() {
        Project project = ProjectFactory.getProject();
        loginStep.auth();
        dashboardPage.clickAddProjectButton();
        addProjectPage.isPageOpened()
                .createNewProject(project);
        int projectId = projectsPage.getProjectId(project.getProjectName());
        projectPage.openProjectById(projectId);
        assertEquals(projectPage.getTitle(),
                project.getProjectName(),
                "Project is not created");
    }

    @Test(testName = "Check that it is possible to delete an existing Project.", priority = 2, groups = {"smoke"})
    @Epic("Project")
    @Owner("Evgeny Sabuk")
    @Description("Check Project deletion.")
    public void checkDeleteProjectTest() {
        loginStep.auth();
        String projectName = projectStep.createProject();
        projectsPage.isPageOpened()
                .deleteProject(projectName);
        assertEquals(projectsPage.getSuccessMessage(),
                "Successfully deleted the project.",
                "Project is not deleted");
    }

    @Test(testName = "Check that it is possible to edit an existing Project.", priority = 2, groups = {"mat"})
    @Epic("Project")
    @Owner("Evgeny Sabuk")
    @Description("Check Project editing.")
    public void checkEditProjectTest() {
        Project project = ProjectFactory.getProject();
        loginStep.auth();
        String projectName = projectStep.createProject();
        projectsPage.isPageOpened()
                .openEditProject(projectName);
        addProjectPage.createNewProject(project);
        assertEquals(projectsPage.getSuccessMessage(),
                "Successfully updated the project.",
                "Project is not updated");
    }
}
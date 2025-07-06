package tests;

import dto.Project;
import dto.ProjectFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {

    @Test
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

    @Test
    public void checkDeleteProjectTest() {
        loginStep.auth();
        String projectName = projectStep.createProject();
        projectsPage.isPageOpened()
                .deleteProject(projectName);
        assertEquals(projectPage.getTitle(),
                project.getProjectName(),
                "Project is not created");
    }

    @Test
    public void checkEditProjectTest() {
        loginStep.auth();
        String projectName = projectStep.createProject();
        projectsPage.isPageOpened()
                .openEditProject(projectName);
    }

    @Test
    public void checkIdTest() {
        loginStep.auth();
        String projectName = projectStep.createProject();
        projectsPage.isPageOpened()
                .getProjectId(projectName);
    }
}
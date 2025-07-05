package tests;

import dto.Project;
import dto.ProjectFactory;
import dto.TestRun;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {

    @Test
    public void checkCreateProjectTest() {
        Project project = ProjectFactory.getProject();
        loginStep.auth();
        dashboardPage.clickAddProjectButton();
        addProjectPage.isPageOpened()
                .createNewProject(project);

    }

    @Test
    public void checkDeleteProjectTest() {
        loginStep.auth();
        String projectName = projectStep.createProject();
        projectsPage.isPageOpened()
                        .deleteProject(projectName);
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
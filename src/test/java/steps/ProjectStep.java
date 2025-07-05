package steps;

import dto.Project;
import dto.ProjectFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.AddProjectPage;
import pages.DashboardPage;
import pages.ProjectPage;
import pages.ProjectsPage;

@Log4j2
public class ProjectStep {

    WebDriver driver;
    DashboardPage dashboardPage;
    AddProjectPage addProjectPage;
    ProjectsPage projectsPage;
    ProjectPage projectPage;

    public ProjectStep(WebDriver driver) {
        this.driver = driver;
        addProjectPage = new AddProjectPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectsPage = new ProjectsPage(driver);
        projectPage = new ProjectPage(driver);
    }

    public String createProject() {
        Project project = ProjectFactory.getProject();
        log.info("Project is created with a name '{}'", project.getProjectName());
        dashboardPage.clickAddProjectButton();
        addProjectPage.isPageOpened()
                      .createNewProject(project);
        return project.getProjectName();
    }

    public void createOpenProject() {
        Project project = ProjectFactory.getProject();
        log.info("Project is created with a name '{}'", project.getProjectName());
        dashboardPage.clickAddProjectButton();
        addProjectPage.isPageOpened()
                .createNewProject(project);
        projectsPage.isPageOpened();
        int projectId = projectsPage.getProjectId(project.getProjectName());
        projectPage.openProjectById(projectId);
    }
}
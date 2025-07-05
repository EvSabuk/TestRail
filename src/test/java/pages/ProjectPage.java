package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ProjectPage extends BasePage{

    private static final By EDIT_BUTTON = By.xpath("//a[@data-testid='editProjectButton']"),
            MILESTONES_BUTTON = By.xpath("//div[@class='sidebar-layout-content']/descendant::a[starts-with(normalize-space(.), 'Milestone')]"),
            TEST_RUNS_AND_RESULTS_BUTTON = By.xpath("//div[@class='sidebar-layout-content']/descendant::a[starts-with(normalize-space(.), 'Test Runs & Results')]"),
            TEST_CASES_BUTTON = By.xpath("//div[@class='sidebar-layout-content']/descendant::a[starts-with(normalize-space(.), 'Test Cases')]");
    private static final String URL = BASE_URL + "index.php?/projects/overview/";

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProjectPage open() {
        log.info("Opening selected 'Project'");
        driver.get(BASE_URL + "index.php?/admin/projects/overview");
        return this;
    }

    @Override
    public ProjectPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(EDIT_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Project' page is not opened");
        }
        return this;
    }

    public void openProjectById(int projectId) {
        driver.get(URL + projectId);
    }

    public MilestonesPage openMilestonesPage() {
        log.info("Opening the 'Milestone' page for the project");
        driver.findElement(MILESTONES_BUTTON).click();
        return new MilestonesPage(driver);
    }

    public TestRunPage openTestRunPage() {
        log.info("Opening the 'Test Runs & Results' page for the project");
        driver.findElement(TEST_RUNS_AND_RESULTS_BUTTON).click();
        return new TestRunPage(driver);
    }

    public TestRunPage openTestCasesPage() {
        log.info("Opening the 'Test Cases' page for the project");
        driver.findElement(TEST_CASES_BUTTON).click();
        return new TestRunPage(driver);
    }
}

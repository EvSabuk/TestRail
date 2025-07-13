package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ProjectPage extends BasePage {

    private static final By EDIT_BUTTON = By.xpath("//a[@data-testid='editProjectButton']"),
            PROJECT_OVERVIEW_BUTTON = By.xpath(
                    "//div[@class='sidebar-layout-content']" +
                            "/descendant::a[starts-with(normalize-space(.), 'Project Overview')]"),
            MILESTONES_BUTTON = By.xpath(
                    "//div[@class='sidebar-layout-content']" +
                            "/descendant::a[starts-with(normalize-space(.), 'Milestone')]"),
            TEST_RUNS_AND_RESULTS_BUTTON = By.xpath(
                    "//div[@class='sidebar-layout-content']" +
                            "/descendant::a[starts-with(normalize-space(.), 'Test Runs & Results')]"),
            TEST_CASES_BUTTON = By.xpath(
                    "//div[@class='sidebar-layout-content']" +
                            "/descendant::a[starts-with(normalize-space(.), 'Test Cases')]"),
            PAGE_TITLE = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']");
    private static final String PROJECT_OVERVIEW_URI = BASE_URL + "index.php?/projects/overview/";

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProjectPage open() {
        log.info("Opening selected 'Project'");
        driver.get(PROJECT_OVERVIEW_URI);
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

    @Step("Open Project by id.")
    public void openProjectById(int projectId) {
        driver.get(PROJECT_OVERVIEW_URI + projectId);
    }

    @Step("Opening 'Project Overview' tab.")
    public ProjectPage openOverviewTab() {
        log.info("Opening 'Project Overview' tab.");
        driver.findElement(PROJECT_OVERVIEW_BUTTON).click();
        return this;
    }

    @Step("Opening the 'Milestone' page for the project.")
    public MilestonesPage openMilestonesPage() {
        log.info("Opening the 'Milestone' page for the project.");
        driver.findElement(MILESTONES_BUTTON).click();
        return new MilestonesPage(driver);
    }

    @Step("Opening the 'Test Runs & Results' page for the project.")
    public TestRunsPage openTestRunPage() {
        log.info("Opening the 'Test Runs & Results' page for the project.");
        driver.findElement(TEST_RUNS_AND_RESULTS_BUTTON).click();
        return new TestRunsPage(driver);
    }

    @Step("Opening the 'Test Cases' page for the project.")
    public TestRunsPage openTestCasesPage() {
        log.info("Opening the 'Test Cases' page for the project.");
        driver.findElement(TEST_CASES_BUTTON).click();
        return new TestRunsPage(driver);
    }

    @Step("Get title for the 'Project' page.")
    public String getTitle() {
        log.info("Get title for the 'Project' page.");
        return driver.findElement(PAGE_TITLE).getText();
    }
}
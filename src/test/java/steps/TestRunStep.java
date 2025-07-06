package steps;

import dto.TestRuns;
import dto.TestRunsFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.*;

@Log4j2
public class TestRunStep {

    WebDriver driver;
    ProjectPage projectPage;
    TestRunsPage testRunsPage;
    AddTestRunPage addTestRunPage;
    TestRunPage testRunPage;

    public TestRunStep(WebDriver driver) {
        this.driver = driver;
        projectPage = new ProjectPage(driver);
        testRunsPage = new TestRunsPage(driver);
        addTestRunPage = new AddTestRunPage(driver);
        testRunPage = new TestRunPage(driver);
    }

    public void createTestRun() {
        TestRuns testRuns = TestRunsFactory.getTestRun();
        log.info("Test Run is created");
        projectPage.isPageOpened()
                .openTestRunPage();
        testRunsPage.isPageOpened()
                .clickAddTestRun();
        addTestRunPage.createNewTestRun(testRuns);
        testRunPage.isPageOpened();
        projectPage.openOverviewTab();
    }

    public void createTestRunWithCases() {
        TestRuns testRuns = TestRunsFactory.getTestRun();
        log.info("Test Run is created");
        projectPage.isPageOpened()
                .openTestRunPage();
        testRunsPage.isPageOpened()
                .clickAddTestRun();
        addTestRunPage.createNewTestRunWithCases(testRuns);
        testRunPage.isPageOpened();
    }
}
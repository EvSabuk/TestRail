package tests;


import dto.TestRuns;
import dto.TestRunsFactory;
import org.testng.annotations.Test;

public class TestRunTest extends BaseTest{

    @Test
    public void check1() {
        TestRuns testRuns = TestRunsFactory.getTestRun();
        loginStep.auth();
        projectStep.createOpenProject();
        projectPage.isPageOpened()
                .openTestRunPage();
        testRunsPage.isPageOpened()
                .clickAddTestRun();
        addTestRunPage.createNewTestRun(testRuns);

    }

    @Test
    public void check2() {
        TestRuns testRuns = TestRunsFactory.getTestRun();
        loginStep.auth();
        driver.get("https://perfectcar.testrail.io/index.php?/runs/overview/55");
        testRunsPage.isPageOpened()
                .clickAddTestRun();
        addTestRunPage.createNewTestRunWithCases(testRuns);
        testRunPage.addResultsTestRun("Blocked");

    }

    @Test
    public void check3() {
        loginStep.auth();
        projectStep.createOpenProject();
        milestoneStep.createMilestone();
        testCaseStep.createMultipleTestCases(3);
        testRunStep.createTestRunWithCases();
        testRunPage.addResultsTestRun("Blocked");

    }

}

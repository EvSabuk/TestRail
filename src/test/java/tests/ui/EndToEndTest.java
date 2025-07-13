package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndToEndTest extends BaseTest {

    @Test(testName = "Check end-to-end scenery: Creating new project," +
            " creating new Milestone, creating new Test Run, creating test cases, and setting statuses.",
            priority = 1, groups = {"smoke"})
    @Epic("End to End scenery")
    @Owner("Evgeny Sabuk")
    @Description("Check that the test completes all necessary steps to set statuses for created test cases.")
    public void createProjectWithRunsAndSetStatus() {
        loginStep.auth();
        projectStep.createOpenProject();
        milestoneStep.createMilestone();
        testCaseStep.createMultipleTestCases(3);
        testRunStep.createTestRunWithCases();
        testRunPage.addResultsTestRun("Blocked");
        assertEquals(testRunPage.getChartItem("Blocked"),
                "3 Blocked",
                "Test cases have wrong statuses");
    }
}
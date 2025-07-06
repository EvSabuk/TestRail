package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndToEndTest extends BaseTest{

    @Test
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
package tests.ui;

import dto.TestRuns;
import dto.TestRunsFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestRunTest extends BaseTest {

    @Test(testName = "Check that it is possible to create a new Test Run.", priority = 1, groups = {"smoke"})
    @Epic("Test Run")
    @Owner("Evgeny Sabuk")
    @Description("Check Test Run creation.")
    public void checkCreateTestRunTest() {
        TestRuns testRuns = TestRunsFactory.getTestRun();
        loginStep.auth();
        projectStep.createOpenProject();
        projectPage.isPageOpened()
                .openTestRunPage();
        testRunsPage.isPageOpened()
                .clickAddTestRun();
        addTestRunPage.createNewTestRun(testRuns);
        assertEquals(testRunsPage.getSuccessMessage(),
                "Successfully added the new test run.",
                "Test Run is not created");
    }

    @Test(testName = "Check that it is possible to edit an existing Test Run.", priority = 2, groups = {"mat"})
    @Epic("Test Run")
    @Owner("Evgeny Sabuk")
    @Description("Check Test Run editing.")
    public void checkEditTestRunTest() {
        TestRuns testRuns = TestRunsFactory.getTestRun();
        loginStep.auth();
        projectStep.createOpenProject();
        testRunStep.createTestRun();
        projectPage.isPageOpened()
                .openTestRunPage();
        testRunsPage.isPageOpened()
                .clickEditTestRun();
        addTestRunPage.createNewTestRun(testRuns);
        assertEquals(testRunsPage.getSuccessMessage(),
                "Successfully updated the test run.",
                "Test Run is not updated");
    }

    @Test(testName = "Check that it is possible to delete an existing Test Run.", priority = 2, groups = {"smoke"})
    @Epic("Test Run")
    @Owner("Evgeny Sabuk")
    @Description("Check Test Run deletion.")
    public void checkDeleteTestRunTest() {
        loginStep.auth();
        projectStep.createOpenProject();
        testRunStep.createTestRun();
        projectPage.isPageOpened()
                .openTestRunPage();
        testRunsPage.clickDeleteTestRun();
        assertEquals(testRunsPage.getSuccessMessage(),
                "Successfully deleted the test runs.",
                "Test Run is not updated");
    }
}
package tests.ui;

import dto.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class TestCaseTest extends BaseTest {

    @Test(testName = "Check that it is possible to create a new Test Cases.", priority = 1, groups = {"smoke"})
    @Epic("Test Case")
    @Owner("Evgeny Sabuk")
    @Description("Check Test Cases creation.")
    public void checkCreateTestCaseTest() {
        TestCases testCases = TestCasesFactory.getTestCases("Test Cases", "Test Case (Steps)",
                "Functional", "Low", "Review", "Me", "Ranorex",
                TestCasesFactory.generateSteps(1));
        loginStep.auth();
        projectStep.createOpenProject();
        projectPage.isPageOpened()
                .openTestCasesPage();
        testCasesPage.isPageOpened()
                .clickAddTestCase();
        addTestCasesPage.isPageOpened()
                .createTestCase(testCases);
        assertEquals(testCasePage.getSuccessMessage(),
                "Successfully added the new test case. Add another",
                "Test case is not created");
    }

    @Test(testName = "Check that it is possible to edit an existing Test Cases", priority = 2, groups = {"mat"})
    @Epic("Test Case")
    @Owner("Evgeny Sabuk")
    @Description("Check Test Case editing.")
    public void checkEditTestCaseTest() {
        TestCases testCases = TestCasesFactory.getTestCases("Test Cases", "Test Case (Steps)",
                "Functional", "Low", "Ready", "Me", "Ranorex",
                TestCasesFactory.generateSteps(1));
        loginStep.auth();
        projectStep.createOpenProject();
        String testCaseName = testCaseStep.createTestCase();
        projectPage.isPageOpened()
                .openTestCasesPage();
        testCasesPage.isPageOpened()
                .openEditTestCase(testCaseName);
        addTestCasesPage.isPageOpened()
                .updateTitleTestCase(testCases);
        assertEquals(testCasePage.getSuccessMessage(),
                "Successfully updated the test case.",
                "Test case is not updated");
    }

    @Test(testName = "Check that it is possible to delete an existing Test Case.", priority = 2, groups = {"smoke"})
    @Epic("Test Case")
    @Owner("Evgeny Sabuk")
    @Description("Check Test Case deletion.")
    public void checkDeleteTestCaseTest() {
        loginStep.auth();
        projectStep.createOpenProject();
        String testCaseName = testCaseStep.createTestCase();
        projectPage.isPageOpened()
                .openTestCasesPage();
        testCasesPage.clickDeleteTestCase(testCaseName);
        testCasesPage.waitUntilTestCaseDeleted(testCaseName);
        List<String> allTitles = testCasesPage.getAllTestCasesTitle();
        assertFalse(allTitles.contains(testCaseName), "Test case still shown in the list of cases");
    }
}
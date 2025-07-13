package steps.ui;

import dto.TestCases;
import dto.TestCasesFactory;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.AddTestCasesPage;
import pages.ProjectPage;
import pages.TestCasePage;
import pages.TestCasesPage;

@Log4j2
public class TestCaseStep {

    WebDriver driver;
    ProjectPage projectPage;
    TestCasesPage testCasesPage;
    AddTestCasesPage addTestCasesPage;
    TestCasePage testCasePage;

    public TestCaseStep(WebDriver driver) {
        this.driver = driver;
        projectPage = new ProjectPage(driver);
        testCasesPage = new TestCasesPage(driver);
        addTestCasesPage = new AddTestCasesPage(driver);
        testCasePage = new TestCasePage(driver);

    }

    @Step("Creating a Test Case.")
    public String createTestCase() {
        int quantitySteps = 1 + (int) (Math.random() * 4);
        TestCases testCases = TestCasesFactory.getTestCases("Test Cases", "Test Case (Steps)",
                "Functional", "Low", "Review", "Me", "Ranorex",
                TestCasesFactory.generateSteps(quantitySteps));
        log.info("Creating a Milestone with the title '{}.'", testCases.getTestCaseTitle());
        projectPage.openTestCasesPage();
        testCasesPage.isPageOpened()
                .clickAddTestCase();
        addTestCasesPage.isPageOpened()
                .createTestCase(testCases);
        testCasePage.isPageOpened();
        projectPage.openOverviewTab();
        return testCases.getTestCaseTitle();
    }

    public void createMultipleTestCases(int times) {
        for (int i = 0; i < times; i++) {
            createTestCase();
        }
    }
}
package steps;

import dto.TestCases;
import dto.TestCasesFactory;
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

    public void createTestCase() {
        int quantitySteps = 1 + (int) (Math.random() * 4);
        log.info("Test Case is created");
        TestCases testCases = TestCasesFactory.getTestCases("Test Cases", "Test Case (Steps)",
                "Functional", "Low", "Review", "Me", "Ranorex", TestCasesFactory.generateSteps(quantitySteps));
        projectPage.openTestCasesPage();
        testCasesPage.isPageOpened()
                .clickAddTestCase();
        addTestCasesPage.isPageOpened()
                .createTestCase(testCases);
        testCasePage.isPageOpened();
        projectPage.openOverviewTab();
    }

    public void createMultipleTestCases(int times) {
        for (int i = 0; i< times; i++) {
            createTestCase();
        }
    }
}

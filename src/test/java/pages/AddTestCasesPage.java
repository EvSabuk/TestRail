package pages;

import dto.Step;
import dto.TestCases;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Input;
import wrappers.Picklist;
import wrappers.TextArea;
import wrappers.TextAreaSteps;

@Log4j2
public class AddTestCasesPage extends BasePage {

    private static final By TITLE_ADD_TEST_CASE = By.xpath("//div[@class = 'content-header-inner']"),
            SAVE_BUTTON = By.id("accept"),
            ADD_STEP_BUTTON = By.xpath("//a[starts-with(normalize-space(.), 'Add Step')]");
    private static final String TEST_CASE_TITLE = "Title",
            SECTION = "Section",
            TEMPLATE = "Template",
            TYPE = "Type",
            PRIORITY = "Priority",
            STATUS = "Status",
            ASSIGNED_TO = "Assigned To",
            ESTIMATE = "Estimate",
            AUTOMATION_TYPE = "Automation Type",
            PRECONDITIONS = "Preconditions";

    public AddTestCasesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddTestCasesPage open() {
        return this;
    }

    @Override
    public AddTestCasesPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ADD_TEST_CASE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Add Test Case' page is not opened");
        }
        return this;
    }

    public ProjectsPage clickSaveButton() {
        log.info("Click on the 'Save' button on the 'Add Test Case' page");
        driver.findElement(SAVE_BUTTON).click();
        return new ProjectsPage(driver);
    }

    public AddTestCasesPage clickAddStepButton() {
        log.info("Click on the 'Add Step' button");
        driver.findElement(ADD_STEP_BUTTON).click();
        return this;
    }

    public void fillInput(String label, String value) {
        new Input(driver, label).write(value);
    }

    public void fillTextarea(String label, String value) {
        new TextArea(driver, label).write(value);
    }

    public void fillActualResult(int label, String value) {
        new TextAreaSteps(driver, label).writeActualResult(value);
    }

    public void fillExpectedResult(int label, String value) {
        new TextAreaSteps(driver, label).writeExpectedResult(value);
    }

    public void selectPicklist(String label, String value) {
        new Picklist(driver, label).select(value);
    }

    public AddTestCasesPage createTestCase(TestCases testCases) {
        log.info("Creating an account {}", testCases.getTestCaseTitle());
        selectPicklist(SECTION, testCases.getTestCaseSection());
        fillInput(TEST_CASE_TITLE, testCases.getTestCaseTitle());
        selectPicklist(TEMPLATE, testCases.getTestCaseTemplate());
        selectPicklist(TYPE, testCases.getTestCaseType());
        selectPicklist(PRIORITY, testCases.getTestCasePriority());
        selectPicklist(STATUS, testCases.getTestCaseStatus());
        selectPicklist(ASSIGNED_TO, testCases.getTestCaseAssignedTo());
        fillInput(ESTIMATE, testCases.getTestCaseEstimate());
        selectPicklist(AUTOMATION_TYPE, testCases.getTestCaseAutomationType());
        fillTextarea(PRECONDITIONS, testCases.getTestCasePreconditions());
        int count = 0;
        for (Step step : testCases.getSteps()) {
            count++;
            clickAddStepButton();
            fillActualResult(count, step.actualResult());
            fillExpectedResult(count, step.expectedResult());
        }
        clickSaveButton();
        return this;
    }
}

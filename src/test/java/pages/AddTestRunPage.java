package pages;

import dto.TestRuns;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Checkbox;
import wrappers.Input;
import wrappers.TextArea;

@Log4j2
public class AddTestRunPage extends BasePage{


    private static final By TITLE_ADD_TEST_TUN = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']"),
            ADD_TEST_RUN_BUTTON = By.id("accept"),
            ALL_CASES_BUTTON = By.id("selectCasesTreeAll"),
            OK_BUTTON = By.id("selectCasesSubmit"),
    CHANGE_SELECTION_BUTTON = By.xpath("//a[starts-with(normalize-space(.), 'change selection')]");
    private static final String TEST_RUN_REFERENCES = "References",
            TEST_RUN_DESCRIPTION = "Description",
                    SELECT_TEST_CASES = "Select specific test cases";

    public AddTestRunPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddTestRunPage open() {
        return this;
    }

    @Override
    public AddTestRunPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ADD_TEST_TUN));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Add Test Run & Results' page is not opened");
        }
        return this;
    }

    public void fillInput(String label, String value) {
        new Input(driver, label).write(value);
    }

    public void fillTextarea(String label, String value) {
        new TextArea(driver, label).write(value);
    }

    public void clickCheckbox(String label) {
        new Checkbox(driver, label).click(label);
    }

    public TestRunsPage clickAddTestRunButton() {
        log.info("Click on the 'Add Test Run' button on the 'Add Test Run & Results' page");
        driver.findElement(ADD_TEST_RUN_BUTTON).click();
        return new TestRunsPage(driver);
    }

    public TestRunsPage clickChangeSelectionButton() {
        log.info("Click on the 'Change Selection' button on the 'Add Test Run");
        driver.findElement(CHANGE_SELECTION_BUTTON).click();
        return new TestRunsPage(driver);
    }

    public TestRunsPage clickSelectAllButton() {
        log.info("Click on the 'Select All' button on the 'Select Cases window");
        driver.findElement(ALL_CASES_BUTTON).click();
        return new TestRunsPage(driver);
    }

    public TestRunsPage clickOkButton() {
        log.info("Click on the 'Ok' button on the 'Select Cases window");
        driver.findElement(OK_BUTTON).click();
        return new TestRunsPage(driver);
    }

    public AddTestRunPage createNewTestRun(TestRuns testRuns) {
        log.info("Creating an Test Run");
        fillInput(TEST_RUN_REFERENCES, testRuns.getTestRunReferences());
        fillTextarea(TEST_RUN_DESCRIPTION, testRuns.getTestRunDescription());
        clickAddTestRunButton();
        return this;
    }

    public AddTestRunPage createNewTestRunWithCases(TestRuns testRuns) {
        log.info("Creating an Test Run with Cases");
        fillInput(TEST_RUN_REFERENCES, testRuns.getTestRunReferences());
        fillTextarea(TEST_RUN_DESCRIPTION, testRuns.getTestRunDescription());
        clickCheckbox(SELECT_TEST_CASES);
        clickChangeSelectionButton();
        clickSelectAllButton();
        clickOkButton();
        clickAddTestRunButton();
        return this;
    }
}

package pages;

import dto.Milestone;
import dto.TestRun;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Input;
import wrappers.TextArea;

@Log4j2
public class AddTestRunPage extends BasePage{


    private static final By TITLE_ADD_TEST_TUN = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']"),
            ADD_TEST_RUN_BUTTON = By.id("accept");
    private static final String TEST_RUN_REFERENCES = "References",
            TEST_RUN_DESCRIPTION = "Description";

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

    public TestRunPage clickAddTestRunButton() {
        log.info("Click on the 'Add Test Run' button on the 'Add Test Run & Results' page");
        driver.findElement(ADD_TEST_RUN_BUTTON).click();
        return new TestRunPage(driver);
    }

    public AddTestRunPage createNewTestRun(TestRun testRun) {
        log.info("Creating an Test Run");
        fillInput(TEST_RUN_REFERENCES, testRun.getTestRunReferences());
        fillTextarea(TEST_RUN_DESCRIPTION, testRun.getTestRunDescription());
        clickAddTestRunButton();
        return this;
    }
}

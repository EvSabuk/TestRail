package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class TestRunsPage extends BasePage {

    private static final By ADD_TEST_RUN_BUTTON = By.xpath(
            "//div[@id='sidebar']/descendant::a[starts-with(normalize-space(.), 'Add Test Run')]"),
            SUCCESS_MESSAGE_TITLE = By.xpath("//div[@data-testid='messageSuccessDivBox']"),
            EDIT_TEST_RUN_BUTTON = By.xpath("//a[starts-with(normalize-space(.), 'Edit')]"),
            DELETE_TEST_RUN_BUTTON = By.xpath("//a[@class='deleteLink']"),
            OK_BUTTON = By.xpath("//a[@data-testid='caseFieldsTabDeleteDialogButtonOk']");

    public TestRunsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TestRunsPage open() {
        return this;
    }

    @Override
    public TestRunsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TEST_RUN_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Test Runs & Results' page is not opened");
        }
        return this;
    }

    @Step("Click on the 'Edit Test Run' button.")
    public AddTestRunPage clickEditTestRun() {
        log.info("Click on the 'Edit Test Run' button.");
        driver.findElement(EDIT_TEST_RUN_BUTTON).click();
        return new AddTestRunPage(driver);
    }

    @Step("Click on the 'Add Test Run' button.")
    public AddTestRunPage clickAddTestRun() {
        log.info("Click on the 'Add Test Run' button.");
        driver.findElement(ADD_TEST_RUN_BUTTON).click();
        return new AddTestRunPage(driver);
    }

    @Step("Click on the 'Delete' button")
    public AddTestRunPage clickDeleteTestRun() {
        log.info("Click on the 'Delete' button.");
        driver.findElement(DELETE_TEST_RUN_BUTTON).click();
        driver.findElement(OK_BUTTON).click();
        return new AddTestRunPage(driver);
    }

    @Step("Get title for the 'Test Runs' page.")
    public String getSuccessMessage() {
        log.info("Get title for the 'Test Runs' page.");
        return driver.findElement(SUCCESS_MESSAGE_TITLE).getText();
    }
}
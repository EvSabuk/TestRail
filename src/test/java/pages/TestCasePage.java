package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class TestCasePage extends BasePage {

    private static final By EDIT_TEST_CASE_BUTTON = By.xpath(
            "//a[@data-testid='testCaseEditButton']"),
            SUCCESS_MESSAGE_TITLE = By.xpath("//div[@data-testid='messageSuccessDivBox']");

    public TestCasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TestCasePage open() {
        return this;
    }

    @Override
    public TestCasePage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(EDIT_TEST_CASE_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Test Case' page is not opened");
        }
        return this;
    }

    @Step("Get title for the 'Test Case' page")
    public String getSuccessMessage() {
        log.info("Get title for the 'Test Case' page");
        return driver.findElement(SUCCESS_MESSAGE_TITLE).getText();
    }
}
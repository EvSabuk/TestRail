package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class TestRunsPage extends BasePage{

    private static final By ADD_TEST_RUN_BUTTON = By.xpath(
            "//div[@id='sidebar']/descendant::a[starts-with(normalize-space(.), 'Add Test Run')]");

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

    public AddTestRunPage clickAddTestRun() {
        log.info("Click on the 'Add Test Run' button");
        driver.findElement(ADD_TEST_RUN_BUTTON).click();
        return new AddTestRunPage(driver);
    }

}

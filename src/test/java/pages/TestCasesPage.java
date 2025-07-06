package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class TestCasesPage extends BasePage {

    private static final By ADD_TEST_CASE_BUTTON = By.xpath(
            "//div[@id='sidebar']/descendant::a[starts-with(normalize-space(.), 'Add Test Case')]");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TestCasesPage open() {
        return this;
    }

    @Override
    public TestCasesPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TEST_CASE_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Test Cases' page is not opened");
        }
        return this;
    }

    public AddTestCasesPage clickAddTestCase() {
        log.info("Click on the 'Add Test Case' button");
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new AddTestCasesPage(driver);
    }
}

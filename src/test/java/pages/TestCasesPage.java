package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TestCasesPage extends BasePage {

    private static final By ADD_TEST_CASE_BUTTON = By.xpath(
            "//div[@id='sidebar']/descendant::a[starts-with(normalize-space(.), 'Add Test Case')]"),
            DELETE_TEST_RUN_BUTTON = By.xpath("//a[@class='deleteLink']"),
            ALL_TEST_CASE_PILLS = By.xpath("//table//tr//td//span[@class= 'title']"),
            MARK_AS_DELETED_BUTTON = By.xpath(
                    "//div[@id='dialog-ident-casesDeletionDialog']" +
                            "/descendant::a[@data-testid='deleteCaseDialogActionDefault']");
    private static final String EDIT_BUTTON = "//table//tr//td//a[contains(., '%s')]/following::div[2]",
            TEST_CASE_PILL = "//table//tr//td//a[contains(., '%s')]";

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

    @Step("Click on the 'Add Test Case' button.")
    public AddTestCasesPage clickAddTestCase() {
        log.info("Click on the 'Add Test Case' button.");
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new AddTestCasesPage(driver);
    }

    @Step("Editing the test case.")
    public void openEditTestCase(String testCase) {
        log.info("Editing the '{}' test case.", testCase);
        Actions actions = new Actions(driver);
        WebElement hoverElement = driver.findElement(By.xpath(String.format(TEST_CASE_PILL, testCase)));
        actions.moveToElement(hoverElement).perform();
        driver.findElement(By.xpath(String.format(EDIT_BUTTON, testCase))).click();
    }

    @Step("Click on the 'Delete' button.")
    public TestCasePage clickDeleteTestCase(String testCase) {
        log.info("Click on the 'Delete' button.");
        Actions actions = new Actions(driver);
        WebElement hoverElement = driver.findElement(By.xpath(String.format(TEST_CASE_PILL, testCase)));
        actions.moveToElement(hoverElement).perform();
        driver.findElement(DELETE_TEST_RUN_BUTTON).click();
        driver.findElement(MARK_AS_DELETED_BUTTON).click();
        isPageOpened();
        return new TestCasePage(driver);
    }

    @Step("Get titles for all Test Cases.")
    public List<String> getAllTestCasesTitle() {
        isPageOpened();
        return driver.findElements(ALL_TEST_CASE_PILLS)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void waitUntilTestCaseDeleted(String testCaseTitle) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(d -> !getAllTestCasesTitle().contains(testCaseTitle));
    }
}
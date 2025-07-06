package pages;

import dto.Step;
import dto.TestCases;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Checkbox;
import wrappers.Picklist;

@Log4j2
public class TestRunPage extends BasePage {

    private static final By EDIT_TEST_RUN_BUTTON = By.xpath(
            "//a[@data-testid='runTestEditButton']"),
            ADD_RESULT_BUTTON = By.id("addResultSubmit"),
            ADD_RESULTS_BUTTON = By.id("massAddResult");

    private static final String STATUS = "Status",
    ID_CHECKBOX = "ID",
    CHART_LEGEND_ITEM = "//div[@class ='table chart-legend-item']/child::div[contains(., '%s')]//div";

    public TestRunPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TestRunPage open() {
        return this;
    }

    @Override
    public TestRunPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(EDIT_TEST_RUN_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Test Case' page is not opened");
        }
        return this;
    }

    public String getChartItem(String status) {
        log.info("Get the value from the chart legend for '{}' status", status);
        return driver.findElement(By.xpath(String.format(CHART_LEGEND_ITEM, status))).getText();
    }


    public void clickCheckbox(String label) {
        new Checkbox(driver, label).clickInGrid(label);
    }

    public void selectPicklist(String label, String value) {
        new Picklist(driver, label).select(value);
    }

    public TestRunPage clickAddResultButton() {
        log.info("Click on the 'Add Result' button for the window 'Add Result'");
        driver.findElement(ADD_RESULT_BUTTON).click();
        return this;
    }

    public TestRunPage clickAddResultsButton() {
        log.info("Click on the 'Add Results' button and window 'Add Result' is opened");
        driver.findElement(ADD_RESULTS_BUTTON).click();
        return this;
    }

    public TestRunPage addResultsTestRun(String status) {
        log.info("Add status {} for all cases", status);
        clickCheckbox(ID_CHECKBOX);
        clickAddResultsButton();
        selectPicklist(STATUS,status);
        clickAddResultButton();
        return this;
    }
}

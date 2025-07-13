package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class TextAreaSteps {

    WebDriver driver;
    int label;

    public TextAreaSteps(WebDriver driver, int label) {
        this.driver = driver;
        this.label = label;
    }

    public void writeActualResult(String text) {
        log.info("Adding actual result for the '{}' step", label);
        WebElement textArea = driver.findElement(By.xpath(String.format(
                "//table//tr[%s]//td//div[@data-testid='addEditCaseStepContent']", label)));
        textArea.clear();
        textArea.sendKeys(text);
    }

    public void writeExpectedResult(String text) {
        log.info("Adding expected result for the '{}' step", label);
        WebElement textArea = driver.findElement(By.xpath(String.format(
                "//table//tr[%s]//td//div[@data-testid='addEditCaseStepExpected']", label)));
        textArea.clear();
        textArea.sendKeys(text);
    }
}
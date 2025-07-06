package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TextAreaSteps {

    WebDriver driver;
    int label;

    public TextAreaSteps(WebDriver driver, int label) {
        this.driver = driver;
        this.label = label;
    }

    public void writeActualResult( String text) {
        log.info("Adding actual result for the '{}' step", label);
        driver.findElement(By.xpath(String.format(
                        "//table//tr[%s]//td//div[@data-testid='addEditCaseStepContent']", label)))
                .sendKeys(text);
    }

    public void writeExpectedResult( String text) {
        log.info("Adding expected result for the '{}' step", label);
        driver.findElement(By.xpath(String.format(
                        "//table//tr[%s]//td//div[@data-testid='addEditCaseStepExpected']", label)))
                .sendKeys(text);
    }

}

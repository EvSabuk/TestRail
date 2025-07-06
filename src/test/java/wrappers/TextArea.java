package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//label[contains(., 'Preconditions')]/following-sibling::div/child::*
//label[starts-with(normalize-space(.), '%s')]/following-sibling::div[contains(@class, 'textarea')]/child::*


@Log4j2
public class TextArea {

    WebDriver driver;
    String label;

    public TextArea(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.info("Writing '{}' in to '{}'", text, label);
        driver.findElement(By.xpath(String.format(
                "//label[contains(., '%s')]/following-sibling::div/child::*", label)))
               .sendKeys(text);
    }
}
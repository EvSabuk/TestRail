package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class Input {

    WebDriver driver;
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.info("Writing '{}' in to '{}'", text, label);
        WebElement input = driver.findElement(By.xpath(String.format(
                "//label[starts-with(normalize-space(.), '%s')]/following-sibling::input", label)));
        input.clear();
        input.sendKeys(text);
    }
}
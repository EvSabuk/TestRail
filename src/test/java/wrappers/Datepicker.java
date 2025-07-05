package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class Datepicker {

    WebDriver driver;
    String label;

    public Datepicker(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void inputDate(Date text) {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        log.info("Specify '{}' date in the datepicker", label);
        driver.findElement(By.xpath(String.format(
                        "//label[starts-with(normalize-space(.), '%s')]/following-sibling::input"
                        , label)))
                .click();
        driver.findElement(By.xpath("//button[starts-with(normalize-space(.), 'Clear')]")).click();
        driver.findElement(By.xpath(String.format(
                        "//label[starts-with(normalize-space(.), '%s')]/following-sibling::input"
                        , label)))
                .sendKeys(df.format(text));
    }
}

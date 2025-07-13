package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Log4j2
public class Picklist {

    WebDriver driver;
    String label;
    String selectPattern = "//label[starts-with(normalize-space(.), '%s')]/following-sibling::div";

    public Picklist(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.info("Selecting '{}' inside picklist '{}'", option, label);
        driver.findElement(By.xpath(
                String.format(selectPattern, label))).click();
        driver.findElement(By.xpath(String.format(
                selectPattern + "/child::div//li[starts-with(normalize-space(.), '%s')]",
                label, option))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath("//div[@class = 'chosen-container chosen-container-single " +
                        "chosen-with-drop chosen-container-active']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
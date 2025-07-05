package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Log4j2
public abstract class BasePage {

    public static final String BASE_URL = "https://perfectcar.testrail.io/";
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract BasePage open();

    public abstract BasePage isPageOpened();
}
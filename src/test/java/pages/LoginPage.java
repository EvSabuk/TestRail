package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage {

    private static final By USER_NAME_FIELD = By.id("name"),
            PASSWORD_FIELD = By.id("password"),
            LOGIN_BUTTON = By.id("button_primary");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Opening 'Login' page.")
    public LoginPage open() {
        log.info("Opening 'Login' page");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Login' page is not opened");
        }
        return this;
    }

    @Step("Log in with credentials: email '{user}' and password '{password}'.")
    public DashboardPage login(String user, String password) {
        log.info("Log in with credentials: '{}', '{}'.", user, password);
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new DashboardPage(driver);
    }
}
package steps.ui;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import utils.PropertyReader;

@Log4j2
public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Step("Log in in the system.")
    public void auth() {
        log.info("Log in with credentials: '{}', '{}'.", email, password);
        loginPage.open()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
    }
}
package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;

@Log4j2
public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    public void auth() {
        //log.info("Log in with credentials: '{}', '{}'", user, password);
        loginPage.open()
                .isPageOpened()
                .login("","")
                .isPageOpened();
    }
}

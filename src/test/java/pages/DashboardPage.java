package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class DashboardPage extends BasePage{

    private static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public DashboardPage open() {
        log.info("Opening 'Dashboard' page");
        driver.get(BASE_URL + "index.php?/dashboard");
        return this;
    }

    @Override
    public DashboardPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Dashboard' page is not opened");
        }
        return this;
    }

    public AddProjectPage clickAddProjectButton() {
        log.info("Click on the 'Add Project' button and redirect to the 'Add Project' page ");
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new AddProjectPage(driver);
    }
}

package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class MilestonesPage extends BasePage{

    private static final By ADD_MILESTONE_BUTTON = By.xpath(
            "//div[@id='sidebar']/descendant::a[starts-with(normalize-space(.), 'Add Milestone')]");

    public MilestonesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MilestonesPage open() {
        return this;
    }

    @Override
    public MilestonesPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MILESTONE_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Milestones' page is not opened");
        }
        return this;
    }

    public AddMilestonePage clickAddMilestone() {
        log.info("Click on the 'Add Milestone' button");
        driver.findElement(ADD_MILESTONE_BUTTON).click();
        return new AddMilestonePage(driver);
    }
}

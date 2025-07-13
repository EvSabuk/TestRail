package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class MilestonesPage extends BasePage {

    private static final By ADD_MILESTONE_BUTTON = By.xpath(
            "//div[@id='sidebar']/descendant::a[starts-with(normalize-space(.), 'Add Milestone')]"),
            SUCCESS_MESSAGE_TITLE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private static final String EDIT_BUTTON = "//table//tr//td//a[contains(., '%s')]/following::div[1]",
            MILESTONE_PILL = "//table//tr//td//a[contains(., '%s')]";

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

    @Step("Get title for the 'Milestone' page")
    public String getSuccessMessage() {
        log.info("Get title for the 'Milestone' page.");
        return driver.findElement(SUCCESS_MESSAGE_TITLE).getText();
    }

    @Step("Click on the 'Add Milestone' button.")
    public AddMilestonePage clickAddMilestone() {
        log.info("Click on the 'Add Milestone' button.");
        driver.findElement(ADD_MILESTONE_BUTTON).click();
        return new AddMilestonePage(driver);
    }

    @Step("Editing the Milestone.")
    public void openEditMilestone(String malestoneName) {
        log.info("Editing the '{}' Milestone.", malestoneName);
        Actions actions = new Actions(driver);
        WebElement hoverElement = driver.findElement(By.xpath(String.format(MILESTONE_PILL, malestoneName)));
        actions.moveToElement(hoverElement).perform();
        driver.findElement(By.xpath(String.format(EDIT_BUTTON, malestoneName))).click();
    }
}
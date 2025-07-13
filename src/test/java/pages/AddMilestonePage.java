package pages;

import dto.Milestone;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Checkbox;
import wrappers.Datepicker;
import wrappers.Input;
import wrappers.TextArea;
import java.util.Date;

@Log4j2
public class AddMilestonePage extends BasePage {

    private static final By TITLE_ADD_MILESTONE = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']"),
            ADD_MILESTONE_BUTTON = By.id("accept");
    private static final String MILESTONE_NAME = "Name",
            MILESTONE_REFERENCES = "References",
            MILESTONE_DESCRIPTION = "Description",
            MILESTONE_START_DATE = "Start Date",
            MILESTONE_END_DATE = "End Date",
            MILESTONE_IS_COMPLETED = "This milestone is completed";

    public AddMilestonePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddMilestonePage open() {
        return this;
    }

    @Override
    public AddMilestonePage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ADD_MILESTONE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Add Milestones' page is not opened");
        }
        return this;
    }

    public void fillInput(String label, String value) {
        new Input(driver, label).write(value);
    }

    public void clickCheckbox(String label) {
        new Checkbox(driver, label).click(label);
    }

    public void fillTextarea(String label, String value) {
        new TextArea(driver, label).write(value);
    }

    public void fillDatepicker(String label, Date value) {
        new Datepicker(driver, label).inputDate(value);
    }

    @Step("Click on the 'Add Milestone' button on the 'Add Milestone' page.")
    public MilestonesPage clickAddMilestoneButton() {
        log.info("Click on the 'Add Milestone' button on the 'Add Milestone' page.");
        driver.findElement(ADD_MILESTONE_BUTTON).click();
        return new MilestonesPage(driver);
    }

    @Step("Creating an Milestone.")
    public AddMilestonePage createNewMilestone(Milestone milestone) {
        log.info("Creating an milestone with name '{}'.", milestone.getMilestoneName());
        fillInput(MILESTONE_NAME, milestone.getMilestoneName());
        fillInput(MILESTONE_REFERENCES, milestone.getMilestoneReferences());
        fillTextarea(MILESTONE_DESCRIPTION, milestone.getMilestoneDescription());
        fillDatepicker(MILESTONE_START_DATE, milestone.getMilestoneStartDate());
        fillDatepicker(MILESTONE_END_DATE, milestone.getMilestoneEndDate());
        clickCheckbox(MILESTONE_IS_COMPLETED);
        clickAddMilestoneButton();
        return this;
    }
}
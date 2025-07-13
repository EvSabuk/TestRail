package steps.ui;

import dto.Milestone;
import dto.MilestoneFactory;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.AddMilestonePage;
import pages.MilestonesPage;
import pages.ProjectPage;

@Log4j2
public class MilestoneStep {

    WebDriver driver;
    ProjectPage projectPage;
    MilestonesPage milestonesPage;
    AddMilestonePage addMilestonePage;

    public MilestoneStep(WebDriver driver) {
        this.driver = driver;
        projectPage = new ProjectPage(driver);
        milestonesPage = new MilestonesPage(driver);
        addMilestonePage = new AddMilestonePage(driver);
    }

    @Step("Creating a Milestone.")
    public String createMilestone() {
        Milestone milestone = MilestoneFactory.getMilestone();
        log.info("Creating a Milestone with the title '{}'.", milestone.getMilestoneName());
        projectPage.isPageOpened()
                .openMilestonesPage();
        milestonesPage.isPageOpened()
                .clickAddMilestone();
        addMilestonePage.isPageOpened()
                .createNewMilestone(milestone)
                .isPageOpened();
        return milestone.getMilestoneName();
    }
}
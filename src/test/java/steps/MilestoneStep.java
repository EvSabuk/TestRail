package steps;

import dto.Milestone;
import dto.MilestoneFactory;
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

    public void createMilestone() {
        Milestone milestone = MilestoneFactory.getMilestone();
        log.info("Milestone is created with a name '{}'", milestone.getMilestoneName());
        projectPage.isPageOpened()
                .openMilestonesPage();
        milestonesPage.isPageOpened()
                .clickAddMilestone();
        addMilestonePage.isPageOpened()
                .createNewMilestone(milestone)
                .isPageOpened();
    }
}

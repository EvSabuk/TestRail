package tests;

import dto.Milestone;
import dto.MilestoneFactory;
import org.testng.annotations.Test;

public class MilestonesTest extends BaseTest{

    @Test
    public void checkCreateMilestoneTest() {

        loginStep.auth();
        projectStep.createOpenProject();
        projectPage.isPageOpened()
                .openMilestonesPage();
        milestonesPage.isPageOpened()
                .clickAddMilestone();

    }

    @Test
    public void check1() {
        Milestone milestone = MilestoneFactory.getMilestone();
        loginStep.auth();
        projectStep.createOpenProject();
        milestoneStep.createMilestone();

    }
}

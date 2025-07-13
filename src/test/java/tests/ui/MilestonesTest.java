package tests.ui;

import dto.Milestone;
import dto.MilestoneFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MilestonesTest extends BaseTest {

    @Test(testName = "Check that it is possible to create a new Milestone.", priority = 1, groups = {"smoke"})
    @Epic("Milestone")
    @Owner("Evgeny Sabuk")
    @Description("Check Milestone creation.")
    public void checkCreateMilestoneTest() {
        Milestone milestone = MilestoneFactory.getMilestone();
        loginStep.auth();
        projectStep.createOpenProject();
        projectPage.isPageOpened()
                .openMilestonesPage();
        milestonesPage.isPageOpened()
                .clickAddMilestone();
        addMilestonePage.createNewMilestone(milestone);
        assertEquals(milestonesPage.getSuccessMessage(),
                "Successfully added the new milestone.",
                "Milestone is not created");
    }

    @Test(testName = "Check that it is possible to edit an existing MIlestone.", priority = 2, groups = {"mat"})
    @Epic("Milestone")
    @Owner("Evgeny Sabuk")
    @Description("Check Milestone editing.")
    public void checkEditMilestoneTest() {
        Milestone milestone = MilestoneFactory.getMilestone();

        loginStep.auth();
        projectStep.createOpenProject();
        String milestoneName = milestoneStep.createMilestone();
        milestonesPage.isPageOpened()
                .openEditMilestone(milestoneName);
        addMilestonePage.createNewMilestone(milestone);
        assertEquals(milestonesPage.getSuccessMessage(),
                "Successfully updated the milestone.",
                "Milestone is not updated");
    }
}
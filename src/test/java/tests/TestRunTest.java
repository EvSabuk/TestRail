package tests;


import dto.Project;
import dto.ProjectFactory;
import dto.TestRun;
import dto.TestRunFactory;
import org.testng.annotations.Test;

public class TestRunTest extends BaseTest{

    @Test
    public void check1() {
        TestRun testRun = TestRunFactory.getTestRun();
        loginStep.auth();
        projectStep.createOpenProject();
        projectPage.isPageOpened()
                .openTestRunPage();
        testRunPage.isPageOpened()
                .clickAddTestRun();
        addTestRunPage.createNewTestRun(testRun);

    }
}

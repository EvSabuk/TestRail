package tests;


import dto.TestCases;
import dto.TestCasesFactory;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestCaseTest extends BaseTest{


    @Test
    public void check1() {
        TestCases testCases = TestCasesFactory.getTestCases("Test Cases", "Test Case (Steps)",
                "Functional", "Low", "Review", "Me", "Ranorex", TestCasesFactory.generateSteps(3));
        loginStep.auth();
        driver.get("https://perfectcar.testrail.io/index.php?/runs/view/33&group_by=cases:section_id&group_order=asc");
       String i =  driver.findElement(By.xpath("//div[@class ='table chart-legend-item']/child::div[contains(., 'Blocked')]//div")).getText();
System.out.println(i);

    }

    @Test
    public void check2() {
        loginStep.auth();
        projectStep.createOpenProject();
        milestoneStep.createMilestone();
        testCaseStep.createMultipleTestCases(3);
        testRunStep.createTestRun();
    }

    @Test
    public void check3() {
        loginStep.auth();
        driver.get("https://perfectcar.testrail.io/index.php?/suites/view/41");
    }

}

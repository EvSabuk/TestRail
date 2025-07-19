package tests.ui;

import api.adapters.DeleteAllProjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import listeners.TestListener;
import steps.api.DeleteProjectStep;
import steps.ui.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import static utils.AllureUtils.takeScreenshot;

@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    protected SoftAssert softAssert;
    LoginStep loginStep;
    ProjectStep projectStep;
    MilestoneStep milestoneStep;
    TestRunStep testRunStep;
    TestCaseStep testCaseStep;
    DashboardPage dashboardPage;
    AddProjectPage addProjectPage;
    ProjectsPage projectsPage;
    ProjectPage projectPage;
    AddMilestonePage addMilestonePage;
    MilestonesPage milestonesPage;
    TestRunsPage testRunsPage;
    AddTestRunPage addTestRunPage;
    TestCasesPage testCasesPage;
    AddTestCasesPage addTestCasesPage;
    TestCasePage testCasePage;
    TestRunPage testRunPage;
    protected DeleteProjectStep projectAPIStep;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Setup browser")
    public WebDriver setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            if (System.getProperty("headless", "true").equals("true")) {
                //options.addArguments("--headless=new");
            }
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            driver = new ChromeDriver(options);
            ((ChromeDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride", Map.of(
                    "width", 1920,
                    "height", 1080,
                    "deviceScaleFactor", 1,
                    "mobile", false
            ));
        }
        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
        loginStep = new LoginStep(driver);
        addProjectPage = new AddProjectPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectStep = new ProjectStep(driver);
        projectsPage = new ProjectsPage(driver);
        projectPage = new ProjectPage(driver);
        addMilestonePage = new AddMilestonePage(driver);
        milestonesPage = new MilestonesPage(driver);
        milestoneStep = new MilestoneStep(driver);
        testRunsPage = new TestRunsPage(driver);
        addTestRunPage = new AddTestRunPage(driver);
        testRunStep = new TestRunStep(driver);
        testCasesPage = new TestCasesPage(driver);
        addTestCasesPage = new AddTestCasesPage(driver);
        testCaseStep = new TestCaseStep(driver);
        testCasePage = new TestCasePage(driver);
        testRunPage = new TestRunPage(driver);
        projectAPIStep = new DeleteProjectStep();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return driver;
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true, description = "Clean account")
    public void cleanAccount() {
        new DeleteAllProjects().deleteAllProjects();
    }
}
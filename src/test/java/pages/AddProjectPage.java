package pages;

import dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Checkbox;
import wrappers.Input;
import wrappers.TextArea;

@Log4j2
public class AddProjectPage extends BasePage {

    private static final By TITLE_PROJECT = By.id("projects-tabs-project"),
            TITLE_DEFECTS = By.id("projects-tabs-defects"),
            TITLE_REFERENCES = By.id("projects-tabs-references"),
            SAVE_BUTTON = By.id("accept");
    private static final String PROJECT_NAME = "Name",
            ANNOUNCEMENT = "Announcement",
            DEFECT_VIEW_URL = "Defect View Url",
            DEFECT_ADD_URL = "Defect Add Url",
            REFERENCE_VIEW_URL = "Reference View Url",
            REFERENCE_ADD_URL = "Reference Add Url",
            SHOW_THE_ANNOUNCEMENT = "Show the announcement on the overview page",
            TEST_CASE_APPROVALS = "Enable Test Case Approvals",
            NEW_PROJECT_URI = "index.php?/admin/projects/add/1";

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddProjectPage open() {
        log.info("Opening 'Add Project' page");
        driver.get(BASE_URL + NEW_PROJECT_URI);
        return this;
    }

    @Override
    public AddProjectPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_PROJECT));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Add Project' page is not opened");
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

    @Step("Click on the 'Add Project' button on the 'Add Project' page.")
    public ProjectsPage clickSaveButton() {
        log.info("Click on the 'Add Project' button on the 'Add Project' page.");
        driver.findElement(SAVE_BUTTON).click();
        return new ProjectsPage(driver);
    }

    @Step("Open 'Defect' subtab.")
    public AddProjectPage openDefectSubTab() {
        log.info("Open 'Defect' subtab.");
        driver.findElement(TITLE_DEFECTS).click();
        return new AddProjectPage(driver);
    }

    @Step("Open 'Reference' subtab.")
    public AddProjectPage openReferenceSubTab() {
        log.info("Open 'Reference' subtab.");
        driver.findElement(TITLE_REFERENCES).click();
        return new AddProjectPage(driver);
    }

    @Step("Scrolling down 'Add Project' page.")
    public void scrollDownPage() {
        log.info("Scrolling down 'Add Project' page.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
    }

    @Step("Scrolling up 'Add Project' page.")
    public void scrollUpPage() {
        log.info("Scrolling up 'Add Project' page.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-500)");
    }

    @Step("Creating the project.")
    public AddProjectPage createNewProject(Project project) {
        log.info("Creating the project '{}'.", project.getProjectName());
        fillInput(PROJECT_NAME, project.getProjectName());
        fillTextarea(ANNOUNCEMENT, project.getAnnouncement());
        clickCheckbox(SHOW_THE_ANNOUNCEMENT);
        scrollDownPage();
        clickCheckbox(TEST_CASE_APPROVALS);
        scrollUpPage();
        openDefectSubTab();
        fillInput(DEFECT_VIEW_URL, project.getDefectViewUrl());
        fillInput(DEFECT_ADD_URL, project.getDefectAddUrl());
        scrollUpPage();
        openReferenceSubTab();
        fillInput(REFERENCE_VIEW_URL, project.getReferenceViewUrl());
        fillInput(REFERENCE_ADD_URL, project.getReferenceAddUrl());
        clickSaveButton();
        return this;
    }
}
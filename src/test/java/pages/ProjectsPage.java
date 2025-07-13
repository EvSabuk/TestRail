package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Checkbox;

@Log4j2
public class ProjectsPage extends BasePage {

    private static final By ADD_PROJECT_BUTTON = By.xpath(
            "//a[starts-with(normalize-space(.), 'Add Project')]"),
            OK_BUTTON = By.xpath("//a[@data-testid='caseFieldsTabDeleteDialogButtonOk']"),
            SUCCESS_MESSAGE_TITLE = By.xpath("//div[@data-testid='messageSuccessDivBox']");

    private static final String DELETE_BUTTON = "//table//tr//td//a[contains(., '%s')]/following::td[2]",
            DELETE_THIS_PROJECT_CHECKBOX = "Yes, delete this project (cannot be undone)",
            EDIT_BUTTON = "//table//tr//td//a[contains(., '%s')]/following::td[1]",
            PROJECT_URL = "//table//tr//td//a[contains(., '%s')]",
            PROJECTS_PAGE_URI = BASE_URL + "index.php?/admin/projects/overview";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckbox(String label) {
        new Checkbox(driver, label).click(label);
    }

    @Override
    public ProjectsPage open() {
        log.info("Opening 'Projects' page");
        driver.get(PROJECTS_PAGE_URI);
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The 'Projects' page is not opened");
        }
        return this;
    }

    @Step("Get title for the 'Projects' page.")
    public String getSuccessMessage() {
        log.info("Get title for the 'Projects' page.");
        return driver.findElement(SUCCESS_MESSAGE_TITLE).getText();
    }

    @Step("Deleting the project.")
    public void deleteProject(String projectName) {
        log.info("Deleting the '{}' project.", projectName);
        driver.findElement(By.xpath(String.format(DELETE_BUTTON, projectName))).click();
        clickCheckbox(DELETE_THIS_PROJECT_CHECKBOX);
        driver.findElement(OK_BUTTON).click();
        isPageOpened();
    }

    @Step("Editing the project.")
    public void openEditProject(String projectName) {
        log.info("Editing the '{}' project.", projectName);
        driver.findElement(By.xpath(String.format(EDIT_BUTTON, projectName))).click();
    }

    @Step("Get ID for the project.")
    public int getProjectId(String projectName) {
        log.info("Get ID for the '{}' project.", projectName);
        String href = driver.findElement(By.xpath(String.format(PROJECT_URL, projectName))).getDomAttribute("href");
        return Integer.parseInt(href.substring(href.lastIndexOf('/') + 1));
    }
}
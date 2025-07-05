package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AddTestCasesPage extends BasePage{
    public AddTestCasesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddTestCasesPage open() {
        return this;
    }

    @Override
    public AddTestCasesPage isPageOpened() {
        return this;
    }
}

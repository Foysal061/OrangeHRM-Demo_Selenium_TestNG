package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    @FindBy(className = "oxd-userdropdown-img")
    public WebElement btnProfileImage;
    @FindBy(partialLinkText = "Logout")
    public WebElement linkLogout;

    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

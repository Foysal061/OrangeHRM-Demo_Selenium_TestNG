package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeePage {
    @FindBy(className = "oxd-button")
    public List<WebElement> oxdbutton;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(css ="[type=submit]")
    public WebElement btnSubmit;
    @FindBy(className = "oxd-switch-input")
    WebElement toggleButton;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtUserCreds;

    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> drpdnNltyBldGrp;

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> menuItem;

    @FindBy(className = "data")
    public List<WebElement> dataClass;

    @FindBy(className = "oxd-text")
    public List<WebElement> sideBar;

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    public List<WebElement> topBarList;

    @FindBy(className = "oxd-table-cell")
    public List<WebElement> tableCellList;
    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createEmployee(String firstName, String lastName, String userName, String password, String confirmPassword) throws InterruptedException {
        Thread.sleep(2000);
        oxdbutton.get(2).click();
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        Thread.sleep(1000);
        toggleButton.click();
        Thread.sleep(1000);
        txtUserCreds.get(5).sendKeys(userName);
        Thread.sleep(1000);
        txtUserCreds.get(6).sendKeys(password);
        Thread.sleep(1000);
        txtUserCreds.get(7).sendKeys(confirmPassword);
        Thread.sleep(1000);
        btnSubmit.click();

    }
}

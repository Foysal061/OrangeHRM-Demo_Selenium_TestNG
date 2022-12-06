package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeePage {
    @FindBy(className = "oxd-button")
    public List<WebElement> btnAddEmployee;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(css ="[type=submit]")
    public WebElement btnSubmit;
    @FindBy(className = "oxd-switch-input")
    WebElement toggleButton;
    @FindBy(className = "oxd-input")
    List<WebElement> txtUserCreds;

    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> drpdnNltyBldGrp;

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> menuItem;
    @FindBy(className = "oxd-select-text")
    public List<WebElement> nationality;
    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createEmployee(String firstName, String lastName, String userName, String password, String confirmPassword) {
        btnAddEmployee.get(2).click();
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        toggleButton.click();
        txtUserCreds.get(5).sendKeys(userName);
        txtUserCreds.get(6).sendKeys(password);
        txtUserCreds.get(7).sendKeys(confirmPassword);
        btnSubmit.click();
    }
}

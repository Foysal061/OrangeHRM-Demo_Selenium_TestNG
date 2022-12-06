package testrunner;

import Pages.EmployeePage;
import Pages.LoginPage;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static utils.Utils.generateRandomNumber;

public class EmployeeTestRunner extends Setup {
    @BeforeTest
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage.doLogin("Admin", "admin123");
    }

    @Test
    public void createEmployee() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        EmployeePage employeePage = new EmployeePage(driver);
        Utils utils = new Utils();
        utils.generateRandomdata();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        int randomId = generateRandomNumber(1000, 9999);
        String userName = firstName + randomId;
        String password = "P@ssword123";
        String confirmPassword = password;
        employeePage.createEmployee(firstName, lastName, userName, password, confirmPassword);
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));

        Utils.waitForElement(driver, headerTitle.get(0), 50);
        Assert.assertTrue(headerTitle.get(0).isDisplayed());

        if (headerTitle.get(0).isDisplayed()) {
            utils.saveJsonList(userName, password);
        }
    }
}

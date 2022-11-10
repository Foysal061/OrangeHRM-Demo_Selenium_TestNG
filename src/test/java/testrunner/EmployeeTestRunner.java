package testrunner;

import Pages.EmployeePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import static utils.Utils.generateRandomNumber;

public class EmployeeTestRunner extends Setup {
    @BeforeTest
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage.doLogin("admin", "admin123");
    }

    @Test
    public void createEmployee() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        EmployeePage employeePage = new EmployeePage(driver);
        Utils utils = new Utils();
        utils.generateRandomdata();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        int randomId = generateRandomNumber(1000, 9999);
        String userName = firstName+randomId;
        String password = "P@ssword123";
        String confirmPassword = password;
        employeePage.createEmployee(firstName, lastName, userName, password, confirmPassword);
    }
}

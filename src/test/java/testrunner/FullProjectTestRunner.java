package testrunner;

import Pages.EmployeePage;
import Pages.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

import static utils.Utils.generateRandomNumber;

public class FullProjectTestRunner extends Setup {

    @Test(priority = 1)
    public void visitTheSite() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        String actualSiteUrl = driver.getCurrentUrl();
        String expectedSiteUrl = "https://opensource-demo.orangehrmlive.com";
        Assert.assertTrue(actualSiteUrl.contains(expectedSiteUrl));
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void doLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        String urlActual = driver.getCurrentUrl();
        String ursExpected = "dashboard/index";
        System.out.println(urlActual);
        Assert.assertTrue(urlActual.contains(ursExpected));
        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void createEmployee() throws IOException, ParseException, InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        for (int i = 0; i < 2; i++) {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
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

            Utils.waitForElement(driver, employeePage.topBarList.get(1), 50);
            Assert.assertEquals(employeePage.topBarList.get(1).getText(), "Employee List");

            Utils.waitForElement(driver, headerTitle.get(0), 50);
            if (headerTitle.get(0).isDisplayed()) {
                String actualId = employeePage.txtUserCreds.get(4).getAttribute("value");
                System.out.println(actualId);
                utils.saveJsonList(userName, password, actualId);
            }
        }
        Thread.sleep(3000);
    }



}

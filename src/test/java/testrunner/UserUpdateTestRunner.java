package testrunner;

import Pages.DashboardPage;
import Pages.EmployeePage;
import Pages.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class UserUpdateTestRunner extends Setup {
    DashboardPage dashboardPage;
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        List data = Utils.readJSONArray("./src/test/resources/Users.json");
        JSONObject userObj = (JSONObject) data.get(data.size() - 1);
        String username = (String) userObj.get("username");
        String password = (String) userObj.get("password");
        System.out.println(username);
        System.out.println(password);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(username, password);
        String urlActual = driver.getCurrentUrl();
        String ursExpected = "dashboard/index";
        System.out.println(urlActual);
        Assert.assertTrue(urlActual.contains(ursExpected));
    }

    @Test (priority = 1)
    public void updateUserInfo() throws InterruptedException {
        List<WebElement> menuItems = driver.findElements(By.className("oxd-main-menu-item"));
        menuItems.get(2).click();
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            EmployeePage employeePage = new EmployeePage(driver);
            List<WebElement> buttons = driver.findElements(By.cssSelector("[type=submit]"));
            employeePage.drpdnNltyBldGrp.get(0).click();
            employeePage.drpdnNltyBldGrp.get(0).sendKeys("d");
            employeePage.drpdnNltyBldGrp.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.drpdnNltyBldGrp.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.drpdnNltyBldGrp.get(0).sendKeys(Keys.ENTER);

            Thread.sleep(5000);
            buttons.get(0).click();
            String actNationality = employeePage.drpdnNltyBldGrp.get(0).getText();
            String expNationality = "Dominican";
            //System.out.println(actNationality);
            Assert.assertTrue(actNationality.equals(expNationality));
            Utils.scrollDown(driver);
            employeePage.drpdnNltyBldGrp.get(2).click();
            Utils.scrollDown(driver);
            employeePage.drpdnNltyBldGrp.get(2).sendKeys("a");
            employeePage.drpdnNltyBldGrp.get(2).sendKeys(Keys.ENTER);
            Thread.sleep(4000);
            WebElement button = driver.findElement(By.xpath("//*[@class='orangehrm-custom-fields']//*[@type='submit']"));
            button.click();
            String actBloodGroup = employeePage.drpdnNltyBldGrp.get(2).getText();
            String expBloodGroup = "A+";
            //System.out.println(actNationality);
            Assert.assertTrue(actBloodGroup.equals(expBloodGroup));

        }
    }
    @Test (priority = 2)
    public void doLogout() throws InterruptedException {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImage.click();
        Thread.sleep(4000);
        dashboardPage.linkLogout.click();
        //driver.findElement(By.partialLinkText("Logout")).click();
    }
}

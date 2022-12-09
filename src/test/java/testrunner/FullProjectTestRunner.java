package testrunner;

import Pages.DashboardPage;
import Pages.EmployeePage;
import Pages.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
        //System.out.println(urlActual);
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
                //System.out.println(actualId);
                utils.saveJsonList(userName, password, actualId);
            }
        }
        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void searchAndValidateEmployees() throws IOException, ParseException, InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        List data = Utils.readJSONArray("./src/test/resources/Users.json");
        JSONObject userObj = (JSONObject) data.get(data.size() - 1);
        searchAndValidateEmployee(employeePage, userObj);
        userObj = (JSONObject) data.get(data.size() - 2);
        searchAndValidateEmployee(employeePage, userObj);

    }

    private static void searchAndValidateEmployee(EmployeePage employeePage, JSONObject userObj) throws InterruptedException {
        employeePage.topBarList.get(1).click();
        String actualEmployeeId = (String) userObj.get("Employee Id");
        //System.out.println(actualEmployeeId);
        List<WebElement> element = employeePage.txtUserCreds;
        Thread.sleep(2000);
        employeePage.txtUserCreds.get(1).click();
        Thread.sleep(2000);
        employeePage.txtUserCreds.get(1).sendKeys(actualEmployeeId);
        Thread.sleep(2000);
        employeePage.oxdbutton.get(1).click();
        Thread.sleep(2000);
        String expectedEmployeeId = employeePage.tableCellList.get(1).getText();
        Utils.scrollDown(driver);
        //System.out.println(expectedEmployeeId);
        Assert.assertEquals(actualEmployeeId, expectedEmployeeId);
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void doLogout() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImage.click();
        Thread.sleep(2000);
        dashboardPage.linkLogout.click();
    }

    @Test(priority = 6)
    public void doLoginAsEmployee() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        List data = Utils.readJSONArray("./src/test/resources/Users.json");
        JSONObject userObj = (JSONObject) data.get(data.size() - 1);
        String username = (String) userObj.get("username");
        String password = (String) userObj.get("password");
        Utils.scrollDown(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(username, password);
        String urlActual = driver.getCurrentUrl();
        String ursExpected = "dashboard/index";
        Assert.assertTrue(urlActual.contains(ursExpected));
    }

    @Test(priority = 7)
    public void updateEmployeeInfo() throws InterruptedException {
        List<WebElement> menuItems = driver.findElements(By.className("oxd-main-menu-item"));
        menuItems.get(2).click();
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            List<WebElement> buttons = driver.findElements(By.cssSelector("[type=submit]"));
            EmployeePage employeePage = new EmployeePage(driver);
//            employeePage.txtUserCreds.get(3).click();
//            Thread.sleep(1000);
//            employeePage.txtUserCreds.get(3).clear();
//            Thread.sleep(1000);
//            employeePage.txtUserCreds.get(3).sendKeys("abc");
//            Thread.sleep(2000);

            employeePage.drpdnNltyBldGrp.get(0).click();
            employeePage.drpdnNltyBldGrp.get(0).sendKeys("d");
            employeePage.drpdnNltyBldGrp.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.drpdnNltyBldGrp.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.drpdnNltyBldGrp.get(0).sendKeys(Keys.ENTER);
            buttons.get(0).click();

//            String actNationality = employeePage.drpdnNltyBldGrp.get(0).getText();
//            String expNationality = "Dominican";
//            Assert.assertTrue(actNationality.equals(expNationality));
//            Utils.scrollDown(driver);
//            employeePage.drpdnNltyBldGrp.get(2).click();
//            Utils.scrollDown(driver);
//            employeePage.drpdnNltyBldGrp.get(2).sendKeys("a");
//            employeePage.drpdnNltyBldGrp.get(2).sendKeys(Keys.ENTER);
//            Thread.sleep(3000);
//            WebElement button = driver.findElement(By.xpath("//*[@class='orangehrm-custom-fields']//*[@type='submit']"));
//            button.click();
//            String actBloodGroup = employeePage.drpdnNltyBldGrp.get(2).getText();
//            String expBloodGroup = "A+";
//            Assert.assertTrue(actBloodGroup.equals(expBloodGroup));
        }
    }

    @Test(priority = 8)
    public void verifyEditedInfo() throws InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        Thread.sleep(2000);
        employeePage.sideBar.get(2).click();
        String urlActual = driver.getCurrentUrl();
        System.out.println(urlActual);
        String actNationality = employeePage.drpdnNltyBldGrp.get(0).getText();
        System.out.println(actNationality);
        String expNationality = "Dominican";
        Assert.assertTrue(actNationality.equals(expNationality));
    }

}

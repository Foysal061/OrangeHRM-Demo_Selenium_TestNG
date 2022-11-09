package testrunner;

import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.util.List;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test (priority = 1)
    public void doLogin() {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage.doLogin("admin", "admin123");
        String urlActual = driver.getCurrentUrl();
        String ursExpected = "dashboard/index";
        System.out.println(urlActual);
        Assert.assertTrue(urlActual.contains(ursExpected));
    }

    @Test (priority = 2)
    public void ifProfileImageExists() {
        boolean imageDisplayStatus = loginPage.imgProfile.isDisplayed();    //using page-object-model, through PageFactory
        Assert.assertTrue(imageDisplayStatus);
    }

    @Test (priority = 3)
    public void ifProfileImageExistsInEmployeeList() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        //boolean imageDisplayStatusInEmployeeList = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed(); //not using page-object-model
        boolean imageDisplayStatusInEmployeeList = loginPage.imgProfile.isDisplayed();
        Assert.assertTrue(imageDisplayStatusInEmployeeList);
        Thread.sleep(5000);
    }

    @Test (priority = 4)
    public void selectEmploymentStatus() throws InterruptedException {
        List<WebElement> dropdown = driver.findElements(By.className("oxd-select-text-input"));
        dropdown.get(0).click();
        Thread.sleep(1000);
        for (int i = 0; i < 3; i++) {
            dropdown.get(0).sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
        }
        dropdown.get(0).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("[type=submit]")).click();
    }

    @Test (priority = 5)
    public void getEmploymentStatus() throws InterruptedException {
        Utils.scrollDown(driver);
        WebElement table = driver.findElement(By.className("oxd-table-body"));
        List<WebElement> allRows = table.findElements(By.cssSelector("[role=row]"));

        for (WebElement row : allRows) {
            List<WebElement> cells = table.findElements(By.cssSelector("[role=cell]"));
            System.out.println(cells.get(5).getText());
            Thread.sleep(100);
            Assert.assertTrue(cells.get(5).getText().contains("Full-Time Permanent"));
        }
    }
    @Test (priority = 6)
    public void doLogout() throws InterruptedException {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImage.click();
        Thread.sleep(2000);
        dashboardPage.linkLogout.click();
        Thread.sleep(2000);

        //driver.findElement(By.partialLinkText("Logout")).click();
    }
}

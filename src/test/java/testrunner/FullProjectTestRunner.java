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



}
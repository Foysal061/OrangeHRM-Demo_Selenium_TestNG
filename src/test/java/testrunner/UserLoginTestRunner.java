package testrunner;

import Pages.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class UserLoginTestRunner extends Setup {
    @Test
    public void doLogin() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        List data = Utils.readJSONArray("./src/test/resources/Users.json");
        JSONObject userObj = (JSONObject) data.get(data.size()-1);
        String username = (String) userObj.get("username");
        String password = (String) userObj.get("password");
        System.out.println(username);
        System.out.println(password);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(username,password);
        String urlActual = driver.getCurrentUrl();
        String ursExpected = "dashboard/index";
        System.out.println(urlActual);
        Assert.assertTrue(urlActual.contains(ursExpected));
    }
}

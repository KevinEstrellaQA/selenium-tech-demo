package stepdefinitions;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import pages.HomePage;
import utils.ConfigReader;

public class CommonSteps {

    HomePage home = new HomePage();

    @Given("I am logged in to www.saucedemo.com with valid credentials")
    public void iAmLoggedInSauceDemoWithValidCredentials(){
        home.navigateToHomePage();
        home.loginCredentials(ConfigReader.get("user.standard"), ConfigReader.get("user.password"));
        home.clickLoginButton();
        String expectedUrl = ConfigReader.get("inventory.url");
        String actualUrl = home.getCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The URLs do not match");
    }

}

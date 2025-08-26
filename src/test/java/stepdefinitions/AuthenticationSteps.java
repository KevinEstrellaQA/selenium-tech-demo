package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.ConfigReader;

public class AuthenticationSteps {

    HomePage home = new HomePage();

    //Successful Login
    @Given("I navigate to www.saucedemo.com")
    public void iNavigateToSauceDemo(){
        home.navigateToHomePage();
    }

    @When("I log in with standard user credentials")
    public void iLogInWithValidCredentialsForAStandardUser(){
        home.loginCredentials(ConfigReader.get("user.standard"), ConfigReader.get("user.password"));
        home.clickLoginButton();
    }

    @Then("I see the inventory products page")
    public void iSeeTheInventoryProductsPage(){
        String expectedUrl = ConfigReader.get("inventory.url");
        String actualUrl = home.getCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The URLs do not match");
    }


    //Failed Login
    @When("I log in with locked out user credentials")
    public void iLogInWithLockedOutUserCredentials(){
        home.loginCredentials(ConfigReader.get("user.locked"), ConfigReader.get("user.password"));
        home.clickLoginButton();
    }

    @Then("I see an error message indicating the user is locked out")
    public void iSeeAnErrorMessageIndicatingTheUserIsLockedOut(){
        SoftAssert soft = new SoftAssert();
        String expectedUrl = ConfigReader.get("base.url");
        String actualUrl = home.getCurrentPageUrl();
        soft.assertEquals(actualUrl, expectedUrl, "The URLs do not match");
        boolean isActive = home.isErrorMessageDisplayed();
        String text = home.getErrorText();
        soft.assertTrue(isActive, "The error message is not displayed");
        soft.assertEquals(text, "Epic sadface: Sorry, this user has been locked out.", "The error message text is incorrect");
        soft.assertAll("Error message verification failed" );
    }


    //Invalid credentials
    @When("I log in with invalid user credentials")
    public void iLogInWithInvalidUserCredentials(){
        home.loginCredentials(ConfigReader.get("user.standard"), "invalidPassword");
        home.clickLoginButton();
    }

    @Then("I see an error message 'Username and password do not match any user in this service'")
    public void iSeeAnErrorMessageIndicatingTheCredentialsAreInvalid(){
        boolean isErrorMessage = home.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorMessage, "The error message is not displayed");
        String text = home.getErrorText();
        Assert.assertEquals(text, "Epic sadface: Username and password do not match any user in this service", "The error message text is incorrect");
    }
}

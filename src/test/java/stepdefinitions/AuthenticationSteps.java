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

    @Epic("Authentication Module")
    @Feature("Authentication")
    @Owner("Kevin Estrella")

    @Story("Successful Login")
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

    @Story("Failed Login")
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
        boolean isErrorMessageCorrect = home.isErrorMessageDisplayed();
        soft.assertTrue(isErrorMessageCorrect, "The error message is not displayed or it is incorrect");
        soft.assertEquals(actualUrl, expectedUrl, "The URLs do not match");
        soft.assertAll("Error message verification failed" );
    }
}

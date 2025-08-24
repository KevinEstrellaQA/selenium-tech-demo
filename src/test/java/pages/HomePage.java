package pages;

import utils.ConfigReader;

public class HomePage extends BasePage{

    public HomePage(){
        super(driver);
    }

    private String usernameField = "//input[@id='user-name']";
    private String passwordField = "//input[@id='password']";
    private String loginButton = "//input[@id='login-button']";
    private String errorMessage = "//div[@class='error-message-container error']";

    public void navigateToHomePage(){
        navigateTo(ConfigReader.get("base.url"));
    }

    public void loginCredentials(String user, String password){
        writeText(usernameField, user);
        writeText(passwordField, password);
    }

    public void clickLoginButton(){
        clickElement(loginButton);
    }

    public String getCurrentPageUrl(){
        return getCurrentUrl();
    }

    public boolean isErrorMessageDisplayed(){
        return isErrorMessageDisplayed(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }
}

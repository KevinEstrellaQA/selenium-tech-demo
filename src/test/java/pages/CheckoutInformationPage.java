package pages;

public class CheckoutInformationPage extends BasePage{

    public CheckoutInformationPage(){
        super(driver);
    }

    private String firstNameField = "//input[@id='first-name']";
    private String lastNameField = "//input[@id='last-name']";
    private String postalCodeField = "//input[@id='postal-code']";
    private String continueButton = "//input[@id='continue']";
    private String errorMessage = "//div[@class='error-message-container error']";

    public void enterFirstName(String firstName){
        writeText(firstNameField, firstName);
    }

    public void enterLastName(String lastName){
        writeText(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode){
        writeText(postalCodeField, postalCode);
    }

    public void clickOnContinue(){
        clickElement(continueButton);
    }

    public boolean isErrorMessageDisplayed(){
        return isElementDisplayed(errorMessage);
    }

    public String getErrorText(){
        return getElementText(errorMessage);
    }

}

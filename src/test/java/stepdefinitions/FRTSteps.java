package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;
import pages.FRTHome;
import pages.FRTRecursos;
import pages.FRTSandbox;
import java.util.Arrays;
import java.util.List;

@Epic("Modulo_de_Autenticacion")
@Feature("Login_de_Usuario")
@Story("Login_Exitoso")
@Owner("Juan_Perez")

public class FRTSteps {

    FRTHome homePage = new FRTHome();
    FRTRecursos recursosPage = new FRTRecursos();
    FRTSandbox sandboxPage = new FRTSandbox();

    @Given("I navigate to www.freerangetesters.com")
    public void iNavigateToFRT(){
        homePage.navigateToFRT();
    }

    @When("I go to {word} section using the navigation bar")
    public void iGoThroughEachSectionOfThePage(String section){
        homePage.sectionLinks(section);
    }

    @And("I click on Automation Sandbox")
    public void iClickOnAutomationSandbox(){
        recursosPage.navigateToSandbox();
    }

    @Then("I get the elements from the dropdown")
    public void iGetTheElementsFromTheDropdown(){
        sandboxPage.switchWindow();
        List<String> currentElements = sandboxPage.getElementsFromDropdown();
        List<String> expectedElements = Arrays.asList("Seleccioná un deporte", "Fútbol", "Tennis", "Basketball");
        Assert.assertEquals(currentElements, expectedElements);
    }
}

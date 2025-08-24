package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import pages.BasePage;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = {"hooks", "stepdefinitions"},
        plugin = { "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })

public class TestRunner {
    @AfterClass
    public static void tearDown(){
        BasePage.closeBrowser();
    }
}
package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    //Static WebDriver variable to be shared across all instances of BasePage and its subclasses.
    protected static WebDriver driver;

    //Static WebDriverWait variable to implement explicit waits.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    //Static block to set up the WebDriver using WebDriverManager and initialize the driver instance.
    static {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.edgedriver().setup();
        //Inicializa la variable estática 'driver' con una instancia de ChromeDriver
        //driver = new EdgeDriver();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    //Constructor that accepts a WebDriver instance and assigns it to the static driver variable.
    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    //Static method to navigate to a specified URL.
    public static void navigateTo(String url){
        driver.get(url);
    }

    public static String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    //Private method that finds an element using the provided locator with an explicit wait.
    private WebElement findElement(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    //Public method that receives a locator and performs a click action, implemented with "find" which includes a wait.
    public void clickElement(String locator){
        findElement(locator).click();
    }

    //Public method that receives a locator and writes text into the located element, implemented with "find" which includes a wait.
    public void writeText(String locator, String keysToSend){
        findElement(locator).clear();
        findElement(locator).sendKeys(keysToSend);
    }

    //Public method that retrieves and returns the option of a dropdown (by value).
    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(findElement(locator));
        dropdown.selectByValue(value);
    }

    //Public method that retrieves and returns the option of a dropdown (by index).
    public void selectFromDropdownByIndex(String locator, int index){
        Select dropdown = new Select(findElement(locator));
        dropdown.selectByIndex(index);
    }

    //Public method that retrieves and returns the size of a dropdown.
    public int dropdownSize(String locator){
        Select dropdown = new Select(findElement(locator));
        List<WebElement> dropdownElements = dropdown.getOptions();
        return dropdownElements.size();
    }

    //Public method that retrieves and returns the elements of a dropdown as a list of strings.
    public List<String> elementsFromDropdown(String locator){
        Select dropdown = new Select(findElement(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for(WebElement option : dropdownOptions){
            values.add(option.getText());
        }
        return values;
    }

    //Public method to switch to a new browser window or tab.
    public void switchToWindow(){
        //Save the original handle
        String originalTab = driver.getWindowHandle();
        //Loop through all handles and switch to the new one
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
            }
        }
    }

    public boolean isErrorMessageDisplayed(String locator, String message){
        return findElement(locator).isDisplayed() && findElement(locator).getText().equals(message);
    }

    public static void closeBrowser(){
        if(driver != null){
            driver.quit();
        }
    }

}

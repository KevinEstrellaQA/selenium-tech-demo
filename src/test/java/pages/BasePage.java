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
import java.util.Arrays;
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

    //Method to retrieve the current URL of the browser.
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    //Method to navigate back to the previous page in the browser history.
    public void navigateBack(){
        driver.navigate().back();
    }

    //Method that finds an element using the provided locator with an explicit wait.
    private WebElement findElement(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    //Method to find multiple elements and return their text as a list of strings.
    private List <String> findElements(String locator){
        List<WebElement> webElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
        List<String> elements = new ArrayList<>();
        for(WebElement element : webElements){
            elements.add(element.getText());
        }
        return elements;
    }

    //Method to get a list of elements with the same locator
    public List<String> getElements(String locator){
        return findElements(locator);
    }

    //Method that receives a locator and performs a click action, implemented with "find" which includes a wait.
    public void clickElement(String locator){
        findElement(locator).click();
    }

    //Method that receives a locator and writes text into the located element, implemented with "find" which includes a wait.
    public void writeText(String locator, String keysToSend){
        findElement(locator).clear();
        findElement(locator).sendKeys(keysToSend);
    }

    //Method that retrieves and returns the option of a dropdown (by value).
    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(findElement(locator));
        dropdown.selectByValue(value);
    }

    //Method that retrieves and returns the option of a dropdown (by index).
    public void selectFromDropdownByIndex(String locator, int index){
        Select dropdown = new Select(findElement(locator));
        dropdown.selectByIndex(index);
    }

    //Method that retrieves and returns the size of a dropdown.
    public int dropdownSize(String locator){
        Select dropdown = new Select(findElement(locator));
        List<WebElement> dropdownElements = dropdown.getOptions();
        return dropdownElements.size();
    }

    //Method that retrieves and returns the elements of a dropdown as a list of strings.
    public List<String> elementsFromDropdown(String locator){
        Select dropdown = new Select(findElement(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for(WebElement option : dropdownOptions){
            values.add(option.getText());
        }
        return values;
    }

    //Method to switch to a new browser window or tab.
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

    //Method to get the text of an element located by the provided locator.
    public String getElementText(String locator){
        return findElement(locator).getText();
    }

    //Method to check if an element is displayed on the page.
    public boolean isElementDisplayed(String locator){
        return findElement(locator).isDisplayed();
    }

    //Method to close the browser and quit the WebDriver session.
    public static void closeBrowser(){
        if(driver != null){
            driver.quit();
        }
    }

}

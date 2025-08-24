package pages;

import java.util.List;

public class FRTSandbox extends BasePage{

    public FRTSandbox(){
        super(driver);
    }

    private String dropdown = "//select[@id='formBasicSelect']";

    public List<String> getElementsFromDropdown(){
        return elementsFromDropdown(dropdown);
    }

    public void switchWindow(){
        switchToWindow();
    }

}

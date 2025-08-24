package pages;

public class FRTRecursos extends BasePage{

    public FRTRecursos(){
        super(driver);
    }

    private String sandbox = "//a[normalize-space()='Automation Sandbox' and @href]";

    public void navigateToSandbox(){
        clickElement(sandbox);
    }

}

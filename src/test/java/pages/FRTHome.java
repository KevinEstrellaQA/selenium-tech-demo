package pages;

public class FRTHome extends BasePage{

    public FRTHome(){
        super(driver);
    }

    private String linkSection = "//a[normalize-space()='%s' and @href]";

    //Función para navegar a www.freerangetesters.com
    public void navigateToFRT(){
        navigateTo("https://www.freerangetesters.com/");
    }
    public void sectionLinks(String section){
        clickElement(String.format(linkSection, section));
    }

}

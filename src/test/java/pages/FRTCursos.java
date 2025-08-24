package pages;

public class FRTCursos extends BasePage{

    public FRTCursos(){
        super(driver);
    }

    private String fundamentosLink = "//a[normalize-space()='Introducción al Testing de Software' and @href]";

    public void fundamentosCourse(){
        clickElement(fundamentosLink);
    }
}

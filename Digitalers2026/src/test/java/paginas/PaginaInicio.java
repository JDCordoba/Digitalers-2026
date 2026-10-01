package paginas;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PaginaInicio {
    private WebDriver driver;
    
    @FindBy(linkText = "Signup / Login")
    private WebElement opcionLoginRegistro;
    
    public PaginaInicio(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    
    public void irAPaginaInicio() {
        driver.get("https://automationexercise.com/");
    }
    
    public void irALogin() {
        opcionLoginRegistro.click();
    }
}
package paginas;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class PaginaLogin {
    private WebDriver driver;
    
    @FindBy(css = ".login-form h2")
    private WebElement tituloLogin;


    @FindBy(css = ".signup-form h2")
    private WebElement tituloRegistro;


    @FindBy(name = "name")
    private WebElement nombreRegistro;


    @FindBy(css = "[data-qa='signup-email']")
    private WebElement emailRegistro;


    @FindBy(css = "[data-qa='signup-button']")
    private WebElement botonRegistro;


    @FindBy(css = "[data-qa='login-email']")
    private WebElement emailLogin;


    @FindBy(css = "[data-qa='login-password']")
    private WebElement passwordLogin;


    @FindBy(css = "[data-qa='login-button']")
    private WebElement botonLogin;

    @FindBy(css = ".login-form p")
    private WebElement errorLogin;


    @FindBy(css = ".signup-form p")
    private WebElement errorRegistro;


    public PaginaLogin(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }


    public String obtenerTituloLogin() {
        return tituloLogin.getText();
    }


    public void registrar(String nombre, String email) {
        nombreRegistro.sendKeys(nombre);
        emailRegistro.sendKeys(email);
        botonRegistro.click();
    }


    public void registrarSinEmail(String nombre) {
        nombreRegistro.sendKeys(nombre);
        botonRegistro.click();
    }


    public void iniciarSesion(String email, String password) {
        emailLogin.sendKeys(email);
        passwordLogin.sendKeys(password);
        botonLogin.click();
    }


    public void iniciarSesionSinPassword(String email) {
        emailLogin.sendKeys(email);
        botonLogin.click();
    }


    public String obtenerErrorLogin() {
        return errorLogin.getText();
    }


    public String obtenerErrorRegistro() {
        return errorRegistro.getText();
    }


    public String obtenerTituloRegistro() {
        return tituloRegistro.getText();
    }
}
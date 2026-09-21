import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Laboratorio2 {
	
    // Se declará la variable a nivel de clase (Global)
    private WebDriver driver;

    // Se usa @BeforeEach para que JUnit ejecute esto ANTES de cada @Test
    @BeforeEach
    public void iniciarNavegador() {
        driver = new EdgeDriver(); 
        driver.manage().window().maximize();
        // La esperá implicita: Esperá hasta 5 segundos a que los elementos buscados aparezcan por pantalla.
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    
    
	@Test
	void lab2_E1_localizadores() {
		
		try {
			
			driver.get("https://automationexercise.com/login");
			
			//PartialLinkText = Busca el enlace que contenga la palabra "Products" dentro de su texto.
			WebElement enlaceProducto = driver.findElement(By.partialLinkText("Products"));
			//LinkText = Sirve para localizar un enlace buscando la coincidencia exacta y completa del texto que se muestra en la pantalla.
			WebElement enlaceRegistro = driver.findElement(By.linkText("Signup / Login"));
			
			WebElement nombre = driver.findElement(By.name("name"));
			WebElement bloqueRegistro = driver.findElement(By.className("signup-form"));

			WebElement botonRegistro = driver.findElement(By.xpath("//button[@data-qa=\"signup-button\"]"));
			
			
			//WebElement email = driver.findElement(By.cssSelector("[data-qa='signup-email']"));
			WebDriverWait waitCorto = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebDriverWait waitMedio = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebDriverWait waitLargo = new WebDriverWait(driver, Duration.ofSeconds(60));
            
            //Espera a que el campo de email sea visible en la pantalla antes de interactuar
            WebElement email = waitLargo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='signup-email']")));
			
			
			email.sendKeys("example@email.com");
		
			//Mala practica: WebElement botonRegistro = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button"));
			
			String textoProducto = enlaceProducto.getText();
			String textoEnlaceRegistro = enlaceRegistro.getText();
			
			System.out.println(textoProducto);
			System.out.println(textoEnlaceRegistro);		
			System.out.println("Se muestra campo nombre: " + nombre.isDisplayed());
			System.out.println("Se muestra formulario registro: " + bloqueRegistro.isDisplayed());
			System.out.println("Se muestra boton registro: " + botonRegistro.isDisplayed());
			System.out.println("Se muestra campo email: " + email.isDisplayed());
				
		}finally {
			
			driver.quit();
			
		}
	}
	
	@Disabled
	@Test
	void lab2_E2_select() {
		
		try {
            driver.get("https://www.selenium.dev/selenium/web/formPage.html");

            WebElement lista = driver.findElement(By.name("selectomatic"));
            // Si la etiqueta en el HTML es un <select>, se usa new Select(lista).
            Select opciones = new Select(lista);

            opciones.selectByVisibleText("Four");
            
            System.out.println(opciones.getFirstSelectedOption().getText());
            
        } finally {
            driver.quit();
        }
	}
	
	
	/*
	Espera fija: Esperá 15s.
	Espera implicita: Esperá hasta 15s que pase algo.
	Espera explicita: Esperá a que paso algo, hasta un tiempo máximo.
	*/
	@Disabled
	@Test
	void demoEsperaFija() throws InterruptedException {
		
	    System.out.println("Antes de la pausa");
	    
	    Thread.sleep(15000); // 15 000 milisegundos: 15 segundos
	    
	    System.out.println("Después de la pausa");
	}
	
	

}

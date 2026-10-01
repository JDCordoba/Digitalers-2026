package sinPOM;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Laboratorio3_Ejercicio1 {
	WebDriver driver;
	WebDriverWait wait;
	
	
	@BeforeSuite
	public void setUp() {
		
		driver = new EdgeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@BeforeTest
	public void irUrl() {
		
		driver.get("https://automationexercise.com/login");
	}
	
	@BeforeClass
	public void maxVentana() {
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@BeforeMethod
	public void mensajeInicio() {
		
		System.out.println("Antes del test (Method)");
	}
	
	@Test
	public void registroUsuario() {
		
		WebElement nombre = driver.findElement(By.name("name"));
	    WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='signup-email']")));
	    WebElement botonRegistro = driver.findElement(By.xpath("//button[@data-qa='signup-button']"));

	    nombre.sendKeys("Estudiante Digitalers");
	    email.sendKeys("correo-invalido");
	    
	    String urlAntes = driver.getCurrentUrl();
	    
	    botonRegistro.click();
	    
	    Assert.assertEquals(driver.getCurrentUrl(), urlAntes);
	    Assert.assertTrue(nombre.isDisplayed());
	}
	
	@Test
	public void loginUsuario2() {
		
		System.out.println("Esta sería la prueba 2");
	}
	
	@Test
	public void escribirYLeerTexto() throws IOException {
		
	    File carpeta = new File("Evidencias");
	    
	    if (!carpeta.exists() && !carpeta.mkdirs()) {
	        throw new IOException("No se pudo crear Evidencias");
	    }
	    
	    File archivo = new File(carpeta, "notatestng.txt");
	    
	    try (FileWriter escritor = new FileWriter(archivo)) {
	        escritor.write("Evidencia de la unidad 22 TestNG");
	    }
	    
	    try (FileReader lector = new FileReader(archivo)) {
	        int caracter;
	        while ((caracter = lector.read()) != -1) {
	            System.out.print((char) caracter);
	        }
	    }
	    
	    System.out.println();
	}
	
	
	@AfterMethod
	public void screenshot() throws IOException {
	    if (driver == null) {
	        return;
	    }

	    File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    
	    File imageFile = new File("Evidencias/Test.png");
	    
	    FileUtils.copyFile(screen, imageFile);

	    System.out.println(imageFile.getAbsolutePath());

	}
	
	
	@AfterMethod
	public void mensajeFin() {
		
		System.out.println("Despues del test (Method)");
	}
	
	@AfterClass
	public void findelTest() {
		
		System.out.println("Fin de la prueba");	
	}
	
	@AfterTest
	public void cerraNavegador() {
		
		if(driver != null) {
		driver.quit();
		}
	}
	
	@AfterSuite
	public void finSuite() {
		
		System.out.println("Fin de la suite de pruebas");
	}
}

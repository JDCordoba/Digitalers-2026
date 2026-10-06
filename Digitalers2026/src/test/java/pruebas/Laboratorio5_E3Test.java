package pruebas;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Laboratorio5_E3Test {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void abrirPagina() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");
    }


    @Test
    public void escribirEnAlertaPrompt() {
    	//Hacemos click en el 4to boton "Click Me"
        WebElement cuartoBoton = wait.until(ExpectedConditions.elementToBeClickable(By.id("promtButton")));
        cuartoBoton.click();

        //Verificamos alerta
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alerta.getText(), "Please enter your name");

        //Completamos campo y presionamos aceptar
        alerta.sendKeys("Estudiante Digitalers");
        alerta.accept();

        //Verificamos que aparezca el texto correcto en pantalla
        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("promptResult")));
        Assert.assertTrue(resultado.getText().contains("You entered Estudiante Digitalers"));
    }


    @AfterMethod(alwaysRun = true)
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


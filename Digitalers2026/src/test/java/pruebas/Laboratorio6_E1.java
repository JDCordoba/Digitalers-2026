package pruebas;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Laboratorio6_E1 {
    WebDriver driver;

    @BeforeMethod
    public void abrirNavegador() {
        ChromeOptions opciones = new ChromeOptions();
        opciones.addArguments("--start-maximized");
        opciones.addArguments("--incognito");
        opciones.addArguments("--disable-extensions");

        // Para ejecutar sin mostrar la ventana:
        // opciones.addArguments("--headless=new");

        driver = new ChromeDriver(opciones);
    }


    @Test
    public void abrirChromeConOpciones() {
        driver.get("https://automationexercise.com/");
        Capabilities capacidades = ((RemoteWebDriver) driver).getCapabilities();

        System.out.println("Navegador: " + capacidades.getBrowserName());
        System.out.println("Versión: " + capacidades.getBrowserVersion());

        Assert.assertEquals(capacidades.getBrowserName(), "chrome");
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
    }


    @AfterMethod(alwaysRun = true)
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
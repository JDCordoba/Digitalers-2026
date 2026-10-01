package pruebas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest {
    private WebDriver driver;

    @BeforeTest
    @Parameters("navegador")
    public void abrir(String navegador) {
        if (navegador.equalsIgnoreCase("edge")) {
           driver = new EdgeDriver();
        } else if (navegador.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Navegador no soportado: " + navegador);
        }

        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/products");
    }


    @Test
    public void buscarProducto() {
        driver.findElement(By.id("search_product")).sendKeys("Blue Top");
        driver.findElement(By.id("submit_search")).click();

        Assert.assertTrue(driver.getPageSource().contains("Searched Products"));
    }


    @AfterTest(alwaysRun = true)
    public void cerrar() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


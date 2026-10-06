package pruebas;

import java.time.Duration;
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


public class Laboratorio5_E4Test {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void abrirPagina() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/table.html");
    }


    @Test
    public void leerCeldaConValorOcho() {
        WebElement celda = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[4]/td[3]")));

        String textoCelda = celda.getText();
        System.out.println("El número de la celda es: " + textoCelda);
        Assert.assertEquals(textoCelda, "8");
    }


    @AfterMethod(alwaysRun = true)
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
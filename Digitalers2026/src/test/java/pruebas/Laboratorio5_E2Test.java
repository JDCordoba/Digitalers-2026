package pruebas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Laboratorio5_E2Test {
    WebDriver driver;
    WebDriverWait wait;
    Path carpetaDescargas;

    @BeforeMethod
    public void abrirPagina() throws IOException {
        //Creamos la carpeta donde guardar nuestro archivo
    	carpetaDescargas = Path.of("target", "descargas").toAbsolutePath();
        Files.createDirectories(carpetaDescargas);
        
        //Borramos el archivo en caso de existir (por una prueba anterior)
        Files.deleteIfExists(carpetaDescargas.resolve("sampleFile.jpeg"));


        //Creamos un mapa (guarda información en "pares": clave → valor)
        Map<String, Object> preferencias = new HashMap<>();
        //Definimos una carpeta de descarga default
        preferencias.put("download.default_directory", carpetaDescargas.toString());
        //Definimos que no abra la ventana para seleccionar archivos
        preferencias.put("download.prompt_for_download", false);

        //Creamos el ChromeOptions para que luego podamos configurar el navegador
        EdgeOptions opciones = new EdgeOptions();
        opciones.setExperimentalOption("prefs", preferencias);

        //Abrimos navegador con las opciones configuradas previamente (carpeta y ventana de explorador de archivos)
        driver = new EdgeDriver(opciones);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://demoqa.com/upload-download");
    }

    @Test
    public void cargarArchivo() {
    	//Le decimos dónde está el archivo
        Path archivo = Path.of("src", "test", "resources", "archivo-prueba.txt").toAbsolutePath();
        Assert.assertTrue(Files.exists(archivo), "Debe existir el archivo de prueba");

        //Subimos el archivo
        WebElement inputArchivo = driver.findElement(By.id("uploadFile"));
        inputArchivo.sendKeys(archivo.toString());

        //Verificamos que aparezca la ruta en pantalla
        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploadedFilePath")));
        Assert.assertTrue(resultado.getText().contains("C:\\fakepath\\archivo-prueba.txt"));
    }


    @Test
    public void descargarArchivo() {
    	//Descargamos el archivo de la página
        WebElement botonDescarga = wait.until(ExpectedConditions.elementToBeClickable(By.id("downloadButton")));
        botonDescarga.click();

        //Le decimos la carpeta donde debe esperar el archivo
        Path descargado = carpetaDescargas.resolve("sampleFile.jpeg");
        
        //Le pedimos que espere hasta que haya un archivo
        wait.until(driver -> {
            return Files.exists(descargado);
        });
        
        //Verificamos que exista el archivo descargado sample.jpeg
        Assert.assertTrue(Files.exists(descargado), "Debe descargarse sampleFile.jpeg");
    }

    @AfterMethod(alwaysRun = true)
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


package sinPOM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Laboratorio4_SinPom {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    void abrirPaginaPrincipal() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
    }


    @AfterMethod
    void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    void loginConContrasenaIncorrecta() {
        WebElement opcionLoginRegistro = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login")));
        opcionLoginRegistro.click();
        wait.until(ExpectedConditions.urlContains("/login"));

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='login-email']")));
        WebElement password = driver.findElement(By.cssSelector("[data-qa='login-password']"));
        WebElement botonLogin = driver.findElement(By.cssSelector("[data-qa='login-button']"));

        email.sendKeys("correo.valido@example.com");
        password.sendKeys("ContrasenaIncorrecta123!");
        botonLogin.click();

        WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login-form p")));

        Assert.assertEquals(mensajeError.getText(), "Your email or password is incorrect!");
    }


    @Test
    void loginSinContrasenaPermaneceEnLaPagina() {
        WebElement opcionLoginRegistro = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login")));
        opcionLoginRegistro.click();
        wait.until(ExpectedConditions.urlContains("/login"));

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='login-email']")));
        WebElement botonLogin = driver.findElement(By.cssSelector("[data-qa='login-button']"));

        email.sendKeys("correo.valido@example.com");
        botonLogin.click();

        WebElement tituloLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login-form h2")));

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertEquals(tituloLogin.getText(), "Login to your account");
    }


    @Test
    void registroConEmailExistente() {WebElement opcionLoginRegistro = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login")));
        opcionLoginRegistro.click();
        wait.until(ExpectedConditions.urlContains("/login"));

        WebElement nombre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        WebElement email = driver.findElement(By.cssSelector("[data-qa='signup-email']"));
        WebElement botonRegistro = driver.findElement(By.cssSelector("[data-qa='signup-button']"));

        nombre.sendKeys("Estudiante Digitalers");
        email.sendKeys("test@test.com");
        botonRegistro.click();

        WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".signup-form p")));


        Assert.assertEquals(mensajeError.getText(), "Email Address already exist!");
    }


    @Test
    void registroSinEmailPermaneceEnLaPagina() {
        WebElement opcionLoginRegistro = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login")));
        opcionLoginRegistro.click();
        wait.until(ExpectedConditions.urlContains("/login"));

        WebElement nombre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        WebElement botonRegistro = driver.findElement(By.cssSelector("[data-qa='signup-button']"));

        nombre.sendKeys("Estudiante Digitalers");
        botonRegistro.click();

        WebElement tituloRegistro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".signup-form h2")));

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertEquals(tituloRegistro.getText(), "New User Signup!");
        
    }
}
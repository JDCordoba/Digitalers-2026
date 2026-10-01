package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import paginas.PaginaInicio;
import paginas.PaginaLogin;


public class Laboratorio4_ConPom {
    WebDriver driver;

    @BeforeMethod
    void abrirNavegador() {
    	driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    
    @AfterMethod
    void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test (description = "Intento de login con contraseña incorrecta", priority = 1)
    void loginConContrasenaIncorrecta() {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();

        PaginaLogin login = new PaginaLogin(driver);
        login.iniciarSesion("correo.valido@example.com", "ContrasenaIncorrecta123!");

        Assert.assertEquals(login.obtenerErrorLogin(), "Your email or password is incorrect!");
    }

    @Test (priority = 4)
    void loginSinContrasenaPermaneceEnLaPagina() {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();


        PaginaLogin login = new PaginaLogin(driver);
        login.iniciarSesionSinPassword("correo.valido@example.com");


        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertEquals(login.obtenerTituloLogin(), "Login to your account");
    }


    @Test(priority = 2)

    void registroConEmailExistente() {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();

        PaginaLogin login = new PaginaLogin(driver);
        login.registrar("Estudiante Digitalers", "test@test.com");
        Assert.assertEquals(login.obtenerErrorRegistro(), "Email Address already exist!");
    }


    @Test (priority = 3)
    void registroSinEmailPermaneceEnLaPagina() {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();

        PaginaLogin login = new PaginaLogin(driver);
        login.registrarSinEmail("Estudiante Digitalers");

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertEquals(login.obtenerTituloRegistro(), "New User Signup!");
    }


}
package pruebas;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import paginas.PaginaInicio;
import paginas.PaginaLogin;
import utilidades.DatosExcel;


public class Laboratorio5_E1Test {
    WebDriver driver;

    @BeforeMethod
    public void abrirNavegador() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "datos login invalido")
    public Object[][] datosLoginInvalidos() {
        return new Object[][] {
            {"alumno.uno@example.com", "ClaveIncorrecta123!"},
            {"alumno.dos@example.com", "OtraClaveIncorrecta456!"}
        };
    }
    
    @DataProvider(name = "datos login invalido desde planilla")
    public Object[][] datosLoginInvalidosDesdePlanilla() throws IOException {
        String ruta = "src/test/resources/datos-login.xlsx";
        String hoja = "Login";
        return DatosExcel.leerExcel(ruta, hoja);
    }

    @Test(dataProvider = "datos login invalido")
    public void loginConContrasenaIncorrecta(String email, String password) {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();

        PaginaLogin login = new PaginaLogin(driver);
        login.iniciarSesion(email, password);

        Assert.assertEquals(login.obtenerErrorLogin(), "Your email or password is incorrect!");
    }
    
    
    
//    @Test(dataProvider = "datos login invalido desde planilla")
//    public void loginConContrasenaIncorrectaDatosPlanilla(String email, String password) {
//        PaginaInicio inicio = new PaginaInicio(driver);
//        inicio.irAPaginaInicio();
//        inicio.irALogin();
//
//        PaginaLogin login = new PaginaLogin(driver);
//        login.iniciarSesion(email, password);
//
//        Assert.assertEquals(login.obtenerErrorLogin(), "Your email or password is incorrect!");
//    }
//
//    
//    
    
    @Test(dataProvider = "datos login invalido desde planilla")
    public void loginConContrasenaIncorrectaDatosPlanilla(String email, String password) {
        PaginaInicio inicio = new PaginaInicio(driver);
        inicio.irAPaginaInicio();
        inicio.irALogin();

        PaginaLogin login = new PaginaLogin(driver);
        login.iniciarSesion(email, password);

        // Si ambos campos vienen vacíos, validamos un comportamiento diferente
        if (email.isEmpty() && password.isEmpty()) {
            // Opción A: Validar un mensaje de campo obligatorio si es que existe (ejemplo)
            // Assert.assertEquals(login.obtenerErrorCamposVacios(), "Campos obligatorios");
            
            // Opción B: Simplemente verificar que seguimos en la URL de login y no avanzó
            Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        } else {
            Assert.assertEquals(login.obtenerErrorLogin(), "Your email or password is incorrect!");
        }
    }


    @AfterMethod(alwaysRun = true)
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
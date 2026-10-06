package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;


//ANTES DE EJECUTAR EL TEST
//Ejecutar en MySQL, parado en la base de datos de cursodigitalers2026:

//CREATE USER 'cursodigitalers2026'@'localhost' IDENTIFIED BY 'clave_de_practica';
//GRANT SELECT ON cursodigitalers2026.* TO 'cursodigitalers2026'@'localhost';
//SHOW GRANTS FOR 'cursodigitalers2026'@'localhost';

public class Laboratorio6_AccesoBaseDatosTest {
    @Test
    public void verificarRegistroEnBaseDeDatos() throws SQLException {   
        String dbUrl = "jdbc:mysql://localhost:3306/bddigitalers2026"; //Editar con los datos de tu BBDD
        String username = "cursodigitalers2026"; //Completar con tu usuario
        String password = "clave_de_practica"; //Completar con tu contraseña

        
        String query =
            "SELECT count(*) as cantidad " +
            "FROM navegadoresweb";
        
        try (
        	    // Intenta conexión con Base de datos
        	    Connection conexion = DriverManager.getConnection(dbUrl, username, password);
        	    // Ejecuta sentencia SQL
        	    Statement sentencia = conexion.createStatement();
        	    // Guarda el resultado de la query
        	    ResultSet resultado = sentencia.executeQuery(query)
        	) {
        	    // 👇 AGREGA ESTA LÍNEA AQUÍ PARA MOVER EL PUNTERO A LA PRIMERA FILA
        	    resultado.next(); 
        	    
        	    // getInt obtiene el valor de una columna una vez posicionados en la fila
        	    Assert.assertTrue(resultado.next(), "La consulta no devolvió resultados");
        	    Assert.assertEquals(resultado.getInt("cantidad"), 6);
        	}

    }
}
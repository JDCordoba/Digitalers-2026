package utilidades;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatosExcel {
    public static Object[][] leerExcel(String ruta, String nombreHoja) throws IOException {
        try (
        	//Representa el archivo
        	FileInputStream archivo = new FileInputStream(ruta);
        	
        	//Representa el libro del archivo
        	XSSFWorkbook libro = new XSSFWorkbook(archivo)) {
        	
        	//Representa la hoja del libro
        	XSSFSheet hoja = libro.getSheet(nombreHoja);
        	
        	if (hoja == null) {
        		throw new IllegalArgumentException("No existe la hoja: " + nombreHoja);
        		}

        	//Tomamos la cantidad de filas y columnas que tiene nuestra planilla
            int ultimaFila = hoja.getLastRowNum();
            int columnas = hoja.getRow(0).getPhysicalNumberOfCells();

            //Creamos un objeto que tenga el "tamaño" (cantidad de filas y columnas) que tenga la planilla
            Object[][] datos = new Object[ultimaFila][columnas];
            DataFormatter formato = new DataFormatter();
            
            //Empieza leyendo una fila
            for (int f = 1; f <= ultimaFila; f++) {
                XSSFRow fila = hoja.getRow(f);
                
                if (fila == null) {
                    throw new IllegalArgumentException("La fila " + (f + 1) + " está vacía");
                }
                //Luego, una a una, lee cada columna.
                for (int c = 0; c < columnas; c++) {
                    datos[f - 1][c] = formato.formatCellValue(fila.getCell(c));
                }
            }
            return datos;
        }
    }
}
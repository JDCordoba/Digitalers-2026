import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

public class Laboratorio1 {

	@Test 
	void lab1_test() {
		System.out.println("Hola Mundo!");
	}
	
	
	@Test
	void lab1_E1() {
		WebDriver driver = new FirefoxDriver();
				
		try {
			
			driver.get("https://automationexercise.com/");
			driver.manage().window().maximize();
			
		} finally {
			driver.quit();
		}
				
	}
	
	@Test
	void lab1_E2_plus() {
		WebDriver driver = new EdgeDriver();

		try {
			
			driver.get("https://automationexercise.com/");
			driver.manage().window().maximize();
			
		} finally {
			driver.quit();
		}
				
	}
	
	
	/*@Test	
	void lab1_E2() {
		WebDriver driver = new ChromeDriver();

		try {
			
			driver.get("https://automationexercise.com/");
			driver.manage().window().maximize();
			driver.manage().window().setSize(new Dimension(1360, 768));
			
		} finally {
			driver.quit();
		}
				
	}
	
	*/
	
	@Test
	void lab1_E3() {
		WebDriver driver = new EdgeDriver();
		
		try {
			
			driver.get("https://automationexercise.com/products");
			
			WebElement buscador = driver.findElement(By.id("search_product"));
			buscador.sendKeys("Blue Top");
			
			WebElement botonBuscar = driver.findElement(By.id("submit_search"));
			botonBuscar.click();
			
		} finally {
			
			driver.quit();
		}
		
		
	}
	
	@Test
	void lab1_E4() {
	    WebDriver driver = new EdgeDriver();
	    try {
	        driver.get("https://automationexercise.com/products");
	        WebElement buscador = driver.findElement(By.id("search_product"));
	        buscador.sendKeys("Blue Top");
	        buscador.sendKeys(Keys.ENTER);

	    } finally {
	        driver.quit();
	    }
	}
		
}


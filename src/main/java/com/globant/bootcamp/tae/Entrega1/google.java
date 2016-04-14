package com.globant.bootcamp.tae.Entrega1;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class google {
	private WebDriver driver;
	
	public boolean waitForElement(WebDriver driver, By locator, int seconds){
		if (seconds>0){
			System.out.println("Esperando por elemento "+locator+" por "+seconds+" segundos.");
		}else{
			System.out.println("Buscando si elemento "+locator+" esta presente.");
		}
		try{
			seconds = (seconds == 0 ? 1 : seconds);
			WebDriverWait wait = new WebDriverWait(driver,seconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			driver.findElement(locator);
			return true;
		}catch (NoSuchElementException e){
			return false;
		}
	}
	

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context){
		try {
			//Abre FireFox
			driver = new FirefoxDriver();
			//Maximiza la ventana del FireFox
			driver.manage().window().maximize();
			//Direciona la Google
			driver.get("https://www.google.com.ar");
			//Localiza el Buscador
			WebElement busqueda = driver.findElement(By.xpath("//input[@id='lst-ib']"));
			//reliza la busqueda
			busqueda.sendKeys("river plate");
			//Busca el 3° resultado
			if (this.waitForElement(driver, By.xpath("//div[@id='rso']/div[3]"), 30)){
				//Localiza el 3° resultado
				WebElement resultado = driver.findElement(By.xpath("//div[@id='rso']/div[3]"));
				WebElement linka = resultado.findElement(By.xpath("//a[@class='_Dk']"));
				// Abre la Noticia
				linka.click();
			}
			
		} catch (Exception e) {
		}
	}
		
	@Test(description = "Busqueda en google")
	public void Google() {
		//Comprueba el si exite el elemento h1 en la pagina
		Assert.assertTrue(this.waitForElement(driver, By.xpath("//h1") , 30),"Elemento no encontrado");
		System.out.println("Contenido de H1: "+driver.findElement(By.xpath("//h1")).getText());
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context){
		try {
			//cierra el Navegador
			driver.close();
			driver.quit();
		}catch (Exception e) {
		}
	}
}
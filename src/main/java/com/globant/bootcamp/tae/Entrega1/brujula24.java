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
public class brujula24 {
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
	

	public boolean subcadena(String cadena,String subcadena){
		int respuesta = cadena.indexOf(subcadena);
		if (respuesta!=-1){
			System.out.println("El comentario  \""+cadena+"\" contriene la palabra  \""+subcadena+"\".");
			return true;
		}else{
			System.out.println("El comentario  \""+cadena+"\" NO contiene la palabra  \""+subcadena+"\".");
			return false;
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context){
		try {
			//Abre el Navegador
			driver = new FirefoxDriver();
			//Maximiza la ventana del FireFox
			driver.manage().window().maximize();
			//Se direcciona labrujula
			driver.get("http://labrujula24.com/");
			//localiza la Noticia Principal
			WebElement nota = driver.findElement(By.xpath(".//*[@class='titulo-nota-destacada-a']"));
			//abre la noticia
			nota.click();
			driver.switchTo().defaultContent();
			//Localiza el iFrame
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='fb_ltr']")));
		} catch (Exception e) {
		}
	}
	
	@Test(description = "Busqueda de Comentario con la palabra Noticia")
	public void Noticia() {
		//Comprueba si hay comentarios
		if (this.waitForElement(driver,By.xpath(".//*[@class='_30o4']"), 30)){
			WebElement comentario = driver.findElement(By.xpath("//div[@class='_30o4']"));
			String comment= comentario.getText();
			System.out.println("El comentario es: \""+comment+"\".");
			//Busca la Plabra Noticia en el Comentario
			Assert.assertTrue(this.subcadena(comment, "Noticia"),"LA SUBCADENA NO SE ENCUENTRA");
		}else{
			System.out.print("NO HAY COMENTARIOS");
		}
		
	
		
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context){
		try {
			driver.close();
			driver.quit();
		}catch (Exception e) {
		}
	}	
}

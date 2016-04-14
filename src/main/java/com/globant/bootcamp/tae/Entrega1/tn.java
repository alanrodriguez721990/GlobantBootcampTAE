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

public class tn {
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
	public boolean MayorA(int val1, int val2){
		if (val1>val2){
			System.out.println("La cantidad "+val1+" es mayor a "+val2);
			return true;
		}else if(val1<val2){
			System.out.println("La cantidad "+val1+" es menor a "+val2);			
			return false;
		}else{
			System.out.println("La cantidad "+val1+" es igual a "+val2);			
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
			//Se direciona a TN
			driver.get("http://tn.com.ar/");
			//Busca el contenido de la Noticia que tiene el titulo H1
			if (this.waitForElement(driver, By.xpath("//h1//a"), 30)){
				WebElement noticia = driver.findElement(By.xpath("//h1//a"));
				//Abre la Noticia
				noticia.click();
				//Busca los comentarios
				if (this.waitForElement(driver,By.xpath("//section[@id='comments']/div[@class='subtitle']/a"), 30)){
					WebElement comentarios = driver.findElement(By.xpath("//section[@id='comments']/div[@class='subtitle']/a"));
					//Abre los comentarios
					comentarios.click();
					
				}
			}
		} catch (Exception e) {
		}
	}
	@Test(description = "Busqueda en google")
	public void Google() {
		//Busca la cantidad de Comentarios
		if (this.waitForElement(driver, By.id("total-comments-count"), 30)){
			//Lee los comentarios
			String cantComentarios = driver.findElement(By.id("total-comments-count")).getText();
			//Evalua el resultado del test
			Assert.assertTrue(this.MayorA(Integer.parseInt(cantComentarios), 1),"LA CANTIAD ES 0 O 1.");
		}
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
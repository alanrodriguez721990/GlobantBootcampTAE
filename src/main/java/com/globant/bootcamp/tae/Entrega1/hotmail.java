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

public class hotmail {
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
			return true;
		}else{
			return false;
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context){
		try {
			driver = new FirefoxDriver();
			//Maximiza la ventana del FireFox
			driver.manage().window().maximize();
			//Se direciona a Hotmail
			driver.get("https://Hotmail.com/");
			//Localoza el mail, y password he ingresa los valores 
			WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
			email.sendKeys("alan@hotmail.com");
			WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
			pass.sendKeys("123456789");
			//Localiza el boton IniciarSesion he ingresa
			WebElement button = driver.findElement(By.xpath("//input[@value='Iniciar sesión']"));
			button.click();
			if (this.waitForElement(driver,By.xpath("//*[@id='_ariaId_30']//div[@class='_lvv_y1']"), 30)){
				//Localiza el mail y lo abre
				WebElement correo = driver.findElement(By.xpath("//div[@id='_ariaId_30']//div[@class='_lvv_y1']"));
				correo.click();
			}else{
				System.out.print("No se encontro");
			}
			
		}catch (Exception e){
			
		}
	}
	@Test(description = "Hotmail")
	public void Suport() {
		if (this.waitForElement(driver,By.xpath(".//*[@class='_rp_C6 _rp_D6']"), 30)){
			WebElement info=driver.findElement(By.xpath("//*[@class='_rp_C6 _rp_D6']"));
			//Verifica si el mail es de Support
			Assert.assertTrue(this.subcadena(info.getText(), "support"),"EL MAIL NO ES DE SUPPORT");		
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

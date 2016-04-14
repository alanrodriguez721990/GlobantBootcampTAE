package com.globant.bootcamp.tae.Varios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class face{
	private WebDriver driver;
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context){
		try {
			driver = new FirefoxDriver();
			driver.get("https://www.facebook.com/");
			WebElement email = driver.findElement(By.id("email"));
			email.sendKeys("");
			WebElement pass = driver.findElement(By.id("pass"));
			pass.sendKeys("");
			WebElement btn_inicio = driver.findElement(By.id("u_0_w"));
			btn_inicio.click();
			WebElement pensando = driver.findElement(By.cssSelector(".uiTextareaAutogrow _3en1"));
			pensando.sendKeys("que sos gato");
		
		} catch (Exception e) {
		}
	}

	
	@Test(description = "Inicio Face")
	public void exercise01() {
		//driver.get("http://www.google.com");
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context){
		try {
//			driver.close();
//			driver.quit();
		}catch (Exception e) {
		}
	}	
}
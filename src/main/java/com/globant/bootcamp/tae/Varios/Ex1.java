package com.globant.bootcamp.tae.Varios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Ex1{
	private WebDriver driver;
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context){
		try {
			//abre FireFox
			driver = new FirefoxDriver();
			//se direcciona a la Page
			driver.get("http://tvroom.github.io/selenium-exercises/ex1/");
			System.out.print(driver.findElement(By.cssSelector(".sel_header")).getText());
		} catch (Exception e) {
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context){
		try {
		//	driver.close();
		//	driver.quit();
		}catch (Exception e) {
		}
	}	
	@Test(description = "Exercise")
	public void exercise01() {
		//driver.get("http://www.google.com");
	}
}
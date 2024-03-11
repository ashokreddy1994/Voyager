package com.test.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class Base {
//	public static WebDriver driver;
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before suite");
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser) throws InterruptedException {
		System.out.println(browser);
		
		if(browser.equals("chrome")) {
			driver.set(new ChromeDriver());
			
		}
		else if(browser.equals("edge")) {
			driver.set(new EdgeDriver());
		}
		else {
			System.out.println("no browser");
			Assert.assertEquals(false, true,"no browser matched");
		}
		
		driver.get().get("https://google.com");
		driver.get().manage().window().maximize();
		driver.get().manage().deleteAllCookies();
		Thread.sleep(5000);
		
		
	}
	public WebDriver getDriver() {
		return driver.get();
	}
	
	@AfterMethod
	public void afterMethod() {
		if(driver.get()!=null) {
		driver.get().close();
		System.out.println("driver closed");
		}
	}
	

}

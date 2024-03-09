package com.test.com;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Testerr {

	@Test
	public void calculate() throws Exception {
		System.out.println("Test");
		WebDriver driver=new ChromeDriver();
		driver.get("https://google.com");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.name("q")).sendKeys("Automation");
	//	Thread.sleep(5000);
		driver.close();
		
	}
	
	@Test
	public void calculate1() throws Exception {
		System.out.println("Test");
		WebDriver driver=new EdgeDriver();
		driver.get("https://google.com");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.name("q")).sendKeys("Selenium");
		Thread.sleep(5000);
		driver.close();
		
	}
	
	
}

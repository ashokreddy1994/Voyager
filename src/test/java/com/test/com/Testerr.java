package com.test.com;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Testerr extends Base {
	PageObject objects;
	int id;

	@Test(priority=0)
	public void calculat3() throws Exception {
		System.out.println("test1");
		String methodName=new Object() {}.getClass().getEnclosingMethod().getName();
		logger=extent.createTest(this.getClass().getSimpleName() +" - "+methodName);
		extentTest.set(logger);
		id=10;
		objects=new PageObject(driver.get());
		objects.searchOprations("Selenium");
	}
	
	@Test(priority=1)
	public void calculate4() throws Exception {
		System.out.println("test2");
		String methodName=new Object() {}.getClass().getEnclosingMethod().getName();
		logger=extent.createTest(this.getClass().getSimpleName() +" - "+methodName);
		extentTest.set(logger);
		System.out.println(id);
		objects=new PageObject(driver.get());
		objects.searchOprations("Java");
	}
	
	
}

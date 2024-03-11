package com.test.com;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Tester2 extends Base{
	
	PageObject objects;
	String name;
	@Test(priority=0)
	public void calculate() throws Exception {
		String methodName=new Object() {}.getClass().getEnclosingMethod().getName();
	//	logger=extent.createTest(this.getClass().getSimpleName() +" - "+methodName);
	//	extentTest.set(logger);
		System.out.println("test3");
		name="ashok";
		objects=new PageObject(driver.get());
		objects.searchOprations("WebDriverIo");
	}
	
	@Test(priority=1)
	public void calculate1() throws Exception {
		System.out.println("test4");
		String methodName=new Object() {}.getClass().getEnclosingMethod().getName();
	//	logger=extent.createTest(this.getClass().getSimpleName() +" - "+methodName);
	//	extentTest.set(logger);
		System.out.println(name);
		objects=new PageObject(driver.get());
		objects.searchOprations("NodeJS");
	}
}

package com.test.com;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Tester2 extends Base{
	
	PageObject objects;
	String name;
	@Test(priority=0)
	public void calculate() throws Exception {
		System.out.println("test3");
		name="ashok";
		objects=new PageObject(driver.get());
		objects.searchOprations("WebDriverIo");
	}
	
	@Test(priority=1)
	public void calculate1() throws Exception {
		System.out.println("test4");
		System.out.println(name);
		objects=new PageObject(driver.get());
		objects.searchOprations("NodeJS");
	}
}

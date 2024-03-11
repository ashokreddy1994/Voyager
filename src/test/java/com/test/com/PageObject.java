package com.test.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObject extends Base{
	public WebDriver driver;
	@FindBy(xpath="//*[@name='q']")
	public WebElement search;
	
	public PageObject(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void searchOprations(String data) throws InterruptedException {
		search.sendKeys(data);
		Thread.sleep(5000);
	}

}

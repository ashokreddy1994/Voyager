package com.test.com;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {
//	public static WebDriver driver;
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before suite");
		String timeStamp= new SimpleDateFormat("dd-MM-YYYY-HH-mm-ss").format(Calendar.getInstance().getTime());
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/target/"+timeStamp+"-extent.html");
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
	}
	
	@BeforeTest
	public void beforeTest() {
		
	}
	
	@BeforeClass
	public void beforeClass() {
     
		
		

		logger=extent.createTest(this.getClass().getSimpleName());
		extentTest.set(logger);
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser) throws InterruptedException {
		System.out.println(browser);
	//	logger.info(browser+" browser invoked");
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
	@AfterSuite
	public void afterSuite() {
		extent.flush();
		
	}
	

}

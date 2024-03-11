package com.test.com;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {
//	public static WebDriver driver;
	public String browserName;
	public String screenshotPath;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before suite");
		String timeStamp = new SimpleDateFormat("dd-MM-YYYY-HH-mm-ss").format(Calendar.getInstance().getTime());
		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/target/" + timeStamp + "-extent.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@BeforeTest
	public void beforeTest() {

	}

	@BeforeClass
	public void beforeClass() {

	}

	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser) throws InterruptedException {
		System.out.println(browser);
		browserName = browser;
		if (browser.equals("chrome")) {
			driver.set(new ChromeDriver());

		} else if (browser.equals("edge")) {
			driver.set(new EdgeDriver());
		} else {
			System.out.println("no browser");
			Assert.assertEquals(false, true, "no browser matched");
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
	public void afterMethod(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			extentTest.get().log(Status.FAIL,
					MarkupHelper.createLabel(testResult.getName() + " - Test Case Failed", ExtentColor.RED));

			screenshotPath = getScreenshot(driver.get(), this.getClass().getName());
			extentTest.get().fail("Details", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else if (testResult.getStatus() == ITestResult.SKIP) {
			extentTest.get().log(Status.SKIP,
					MarkupHelper.createLabel(testResult.getName() + " - Test Case SKIPPED", ExtentColor.ORANGE));

		} else if (testResult.getStatus() == ITestResult.SUCCESS) {
			extentTest.get().log(Status.PASS,
					MarkupHelper.createLabel(testResult.getName() + " - Test Case Passed", ExtentColor.GREEN));

			if (driver.get() != null) {
				driver.get().close();
				System.out.println("driver closed");
			}
		}
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();

	}

	public String getScreenshot(WebDriver driver, String test) {
		String timeStamp = new SimpleDateFormat("dd-MM-YY-HH-mm-ss").format(Calendar.getInstance().getTime());
		String screenshotPath = System.getProperty("user.dir") + File.separatorChar + "target" + File.separatorChar
				+ "Screenshots" + File.separatorChar + test + timeStamp + ".jpeg";
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					destFile = new File(screenshotPath);
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			System.out.println("Exception faced while taking screenshot: " + e.getMessage());
			return screenshotPath;
		}
		return screenshotPath;
	}

}

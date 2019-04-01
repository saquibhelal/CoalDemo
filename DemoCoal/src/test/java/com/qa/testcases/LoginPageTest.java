package com.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ExtentReportListener.ExtentReporterNg;
import com.qa.base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	public ExtentReports extent;
	public ExtentTest extentTest;
	String sheetName="Sheet1";
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Rehan Pc");
		extent.addSystemInfo("User Name", "Rehan");
		extent.addSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	
	@BeforeClass
	public void setUp(){
		initializationBrowser();
		loginPage= new LoginPage();
	}
	
	@DataProvider()
	public Object[][] getCoalSheetData(){
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		extentTest = extent.startTest("PageTitleTest");
		String compare="online imported coal forward, spot e-auction kolkata, India";
		//compare.replaceAll("\\s", "");
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title,compare);
	}
 
	
	@Test(priority=2,dataProvider="getCoalSheetData")
	public void loginTest(String usNm,String Lgn) throws InterruptedException{
		extent.startTest("Test Started");
		Thread.sleep(7000);
		loginPage.loginUser(usNm, Lgn);
		
	}
	
	@AfterClass
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest);
		
		System.out.println("======Browser is shutting down=====\n");
		 driver.quit();
	}
}

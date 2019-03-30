package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	String sheetName="sheetName";
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeClass
	public void setUp(){
		initializationBrowser();
		loginPage= new LoginPage();
	}
	
	@DataProvider
	public Object[][] getCoalSheetData(){
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getCoalSheetData")
	public void loginPageTitleTest(){
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Welcome to coaljunction e-auction services");
	}
 
	
	
	@Test(priority=2)
	public void loginTest(String usNm,String Lgn) throws InterruptedException{
		Thread.sleep(7000);
		loginPage.loginUser(usNm, Lgn);
		//homePage=loginPage.login(Pro.getProperty("username"), Pro.getProperty("password"));
		
		
	}
	
	@AfterClass
	public void tearDown(){
		System.out.println("======Browser is shutting down=====\n");
		 driver.quit();
	}
}

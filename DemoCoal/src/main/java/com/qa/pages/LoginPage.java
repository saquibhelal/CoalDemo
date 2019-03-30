package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase {
  
	@FindBy(xpath="//input[@type='text']")
	WebElement userName;
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	@FindBy(xpath="//input[@value='Submit']")
	WebElement submit;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	/*public String validateLoginPage(){
	String valCheck="Welcome to coaljunction e-auction services";
	return valCheck;
}*/
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public void loginUser(String usrName,String pass){
		userName.sendKeys(usrName);
		password.sendKeys(pass);
		submit.click();
	}
	
}

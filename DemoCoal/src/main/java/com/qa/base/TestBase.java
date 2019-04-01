package com.qa.base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.util.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties Pro;
	/*public static EventFiringWebDriver e_driver;
	public static WebEventListner eventListner;*/
	
public TestBase(){
		
		try{
			Pro=new Properties();
			FileInputStream fis=new FileInputStream("C:\\Users\\Rehan\\git\\CoalDemo\\DemoCoal"
					+ "\\src\\main\\java\\co\\qa\\config\\config.properties");
			Pro.load(fis);
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
    
public void initializationBrowser(){
	String browserName=Pro.getProperty("browser");
	
	if(browserName.equalsIgnoreCase("chrome")){
		WebDriverManager.chromedriver().setup();
		
		    driver=new ChromeDriver();	
	}
	else if(browserName.equalsIgnoreCase("FF")){
		WebDriverManager.firefoxdriver().setup();
		
 			driver=new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("IE")){
		WebDriverManager.iedriver().setup();
		
		    driver=new InternetExplorerDriver();
	}
	
	driver.manage().window().maximize();
	//driver.manage().window().setPosition(new Point(0, -1000));
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	driver.get(Pro.getProperty("url"));
	//driver.manage().window().maximize();
}
	
		

}

package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.ProductInfoPage;

public class BaseTest {
	
	
	DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public MyAccountPage accountPage;
	public ProductInfoPage prodInfo;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_properties();
		String bName = prop.getProperty("browser");
		driver=df.init_driver(bName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

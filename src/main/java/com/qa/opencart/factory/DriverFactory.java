package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Gargi
 *
 */
public class DriverFactory {

	public static WebDriver driver;

	public static Properties prop;

	public static OptionsManager op;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is responsible to initialise browser depending on browsername
	 * given in config file
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver init_driver(String browserName) {

		op = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver(op.getChromeOptions());
			//tlDriver.set(new ChromeDriver(op.getChromeOptions()));
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Please pass the correct browser name" + browserName);
		}

		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		// getDriver().manage().window().fullscreen();
		// getDriver().manage().deleteAllCookies();

		return driver;
	}

	public static WebDriver getDriver() {
		driver.get(prop.getProperty("url"));
		return driver;
	}

	/**
	 * This is used to read valuse from config file properties
	 * 
	 * @return properties object
	 */
	public Properties init_properties() {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");// D:\EclipseWorkspace\Oct2020POMSeries\src\test\resources\config\config.properties
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	
	/**
	 * get screenshot
	 * @return 
	 * @throws IOException 
	 */
	
	/**
	 * take sceenshot
	 * Ashot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//File srcFile = new File(src);
		String path = System.getProperty("user.dir")+ "/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			//LOGGER.error("some exception is coming while creating the screenshot");
			e.printStackTrace();
		}
		return path;
	}

}

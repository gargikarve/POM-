package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	
	
	@DataProvider
	public Object[][] getData() {
	Object data[][]=ExcelUtil.getTestData(Constants.REGISTER_DATA_SHEET);
	return data;
	}
	
	@Test(dataProvider="getData")
	public void enterDetailsTest(String fNm, String lNm, String emailtxt, String telph, String pass, String subscribe) throws InterruptedException 
	{
		RegistrationPage regPg= new RegistrationPage(driver);
		regPg.enterDetails(fNm, lNm, emailtxt, telph, pass, subscribe);
		//(fNm,lNm,emailtxt,telph,pass,subscribe);
		//String fNm, String lNm, String emailtxt, String telph, String pass, String repass,String subscribe
		//regPg.getRightMenuList();
	}
//	@Test
//	public void getRightMenuListTest() {
//		regPg.enterDetails();
//		
//	}
}

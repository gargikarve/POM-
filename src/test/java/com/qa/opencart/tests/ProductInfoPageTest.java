package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void setUP() {
		accountPage = loginPage.dologinClick(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void getProductPageTitleTest() {
		accountPage.doSearch("macbook");
		prodInfo = accountPage.selectProduct("macbook");
		String txt = prodInfo.getProductPageTitle("macbook");
		Assert.assertEquals(txt, "Search - macbook");

	}
	
	@Test

	public void getImgsTest() {
		accountPage.doSearch("macbook");
		prodInfo = accountPage.selectProduct("MacBook");
		int cnt = prodInfo.getImgs();
		System.out.println("count is........"+cnt);
		Assert.assertTrue(prodInfo.getImgs()==5);//(prodInfo.getImgs() == 3);
	}
	
	@Test
	public void getProductInfoTest() {
		accountPage.doSearch("macbook");
		prodInfo = accountPage.selectProduct("MacBook");
		Map<String, String> myProdInfo=prodInfo.getProductInfo();
		System.out.println(myProdInfo);
		
		SoftAssert softAssert= new SoftAssert();
		softAssert.assertEquals(myProdInfo.get("name"), "MacBook");
		softAssert.assertEquals(myProdInfo.get("Brand"), "Apple");
		softAssert.assertAll();
	}
}

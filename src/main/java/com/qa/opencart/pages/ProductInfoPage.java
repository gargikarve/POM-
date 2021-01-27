package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;



public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil util;

	By prodHeader = By.cssSelector("#content h1");
	By prodInfo1 = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	By prodPrice = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	By qnty = By.cssSelector("input#input-quantity");
	By addToCart = By.cssSelector("button#button-cart");
	By prodImg = By.cssSelector("a.thumbnail img");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public Map<String, String> getProductInfo() {
		Map<String,String> prodInfoMap= new HashMap<String, String>();
		prodInfoMap.put("name", util.doGetText(prodHeader).trim());
		
		List<WebElement> prodData= util.getElements(prodInfo1);
		for (WebElement e : prodData) {
			String meta[]= e.getText().split(":");
			String meta1=e.getText().split(":")[0].trim();
			String meta2=e.getText().split(":")[1].trim();
			prodInfoMap.put(meta1, meta2);
			
		}
		
		List<WebElement> pricelist= util.getElements(prodPrice);
		prodInfoMap.put("price", pricelist.get(0).getText().trim());
		prodInfoMap.put("exTaxprice", pricelist.get(1).getText().split(":")[1].trim());
		
		
		return prodInfoMap;
		
		
	}
	
	public void enterQuanity(String quantity) {
		util.doSendKeys(qnty, quantity);
	}
	
	public void clickOnAddCart() {
		util.doClick(addToCart);
	}
	
	public int getImgs() {
		List<WebElement> imgList= util.getElements(prodImg);
		System.out.println(imgList.size());
		int cnt= imgList.size();
		return cnt;
	}
	
	public String getProductPageTitle(String prodNm) {
		 return util.waitForPageTitleWithContain(prodNm,4);
	}

}

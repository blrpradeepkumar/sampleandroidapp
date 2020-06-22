package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.amazon.interfaces.Product;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Pradeep Kumar C
 *
 */
public class CartPage implements Product{
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'65 inches')]")
	private WebElement productTitle;
	
	@AndroidFindBy(className = "android.widget.ListView")
	private List<WebElement> productPrice;
	
	/**
	 * Constructor to initialize the cart page.
	 * @param driver - It takes the driver object as parameter.
	 */
	public CartPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Method to return the title in cart page.
	 * @return - product title in cart page.
	 */
	public String getTitleInCartPage() {
		return productTitle.getText();
	}
	
	/**
	 * Method to return the product price in cart page.
	 * @return - product price in cart page.
	 */
	public List<WebElement> getProductPrice() {
		return productPrice;
	}

	/**
	 * Method to return the price of product.
	 */
	@Override
	public double getPrice() {
		String price = productPrice.get(0).getText();
		return Double.parseDouble(price);
	}

	/**
	 * Method to get the product title.
	 */
	@Override
	public String getProductTitle() {
		return productTitle.getText();
	}

}
package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utilities.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchPage {

	//Search Page
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
	private List<WebElement> listOfProducts;

	@AndroidFindBy(xpath = "//*[contains(@text,'See all results for 65-inch tv.')]")
	private WebElement seeAllResultsLink;
	
	//Product page
	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'(65 inches)')]")
	private WebElement productTitle;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'rupees')]")
	private WebElement productPrice;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add to Cart']")
	private WebElement addToCartButton;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Cart']")
	private WebElement cartButton;

	public List<WebElement> getListOfProducts() {
		return listOfProducts;
	}

	public WebElement getSeeAllResultsLink() {
		return seeAllResultsLink;
	}

	public WebElement getAddToCartButton() {
		return addToCartButton;
	}

	public WebElement getCartButton() {
		return cartButton;
	}

	public SearchPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void clickToSeeAllResults() {
		Utilities.tapOnElement(seeAllResultsLink);
	}

	/**
	 * Method to choose a random product.
	 */
	public void chooseARandomProduct() {
		int results = listOfProducts.size();
		int randomProduct = Utilities.generateRandomNumber(results);

		for (int element = 0; element < results; element++) {

			if (element == randomProduct) {
				listOfProducts.get(element).click();
				break;
			}
		}

	}
	
	/**
	 * Method to get product title.
	 * @return - returns product title.
	 */
	public String getProductTitle() {
		return productTitle.getText();
	}
	
	/**
	 * Method to get product price.
	 * @return - returns product price.
	 */
	public String getProductPrice() {
		return productPrice.getText();
	}
	
	/**
	 * Method to click on add to cart button.
	 */
	public void clickOnAddtoCartButton() {
		addToCartButton.click();
	}
	
	/**
	 * Method to click on cart button.
	 */
	public void clickOnCartButton() {
		cartButton.click();
	}
}
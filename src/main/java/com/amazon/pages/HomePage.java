package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utilities.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search']")
	private WebElement searchTextBoxOnHomePage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='65-inch tv']")
	private WebElement searchedProduct;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='English - EN']")
	private WebElement englishCheckBox;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Save Changes']")
	private WebElement saveChangesButton;

	/**
	 * Constructor to initialize homepage. 
	 * @param driver - It takes the driver object as paramater.
	 */
	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to click the English language check box.
	 * @return - returns the WebElement after checking the checkbox.
	 */
	public WebElement getEnglishCheckBox() {
		return englishCheckBox;
	}

	/**
	 * Method to enter the search text.
	 * @param searchText - It takes the searchText as paramater.
	 */
	public void enterDetailsinSearchTextBox(String searchText) {
		Utilities.tapOnElement(getSearchTextBoxOnHomePage());
		getSearchTextBoxOnHomePage().sendKeys(searchText);
	}

	/**
	 * Method to select a product from the list of products.
	 * @param driver - It takes the driver object as parameter.
	 * @return - returns the search page object.
	 */
	public SearchPage selectProductFromList(AndroidDriver<AndroidElement> driver) {
		Utilities.tapOnElement(searchedProduct);
		return new SearchPage(driver);
	}

	/**
	 * Method to click on English check box.
	 */
	public void clickOnEnglishCheckbox() {
		Utilities.tapOnElement(getEnglishCheckBox());
	}

	/**
	 * Method to click on save changes button.
	 */
	public void clickOnSaveChangesButton() {
		Utilities.tapOnElement(getSaveChangesButton());
	}

	/**
	 * Method to get search text box on home page.
	 * @return - returns WebElement.
	 */
	public WebElement getSearchTextBoxOnHomePage() {
		return searchTextBoxOnHomePage;
	}

	/**
	 * Method to get the searched product.
	 * @return - returns the searched product.
	 */
	public WebElement getSearchedProduct() {
		return searchedProduct;
	}

	/**
	 * Method to get the Save changes web element.
	 * @return - returns the SaveChanges button webelement.
	 */
	public WebElement getSaveChangesButton() {
		return saveChangesButton;
	}

}

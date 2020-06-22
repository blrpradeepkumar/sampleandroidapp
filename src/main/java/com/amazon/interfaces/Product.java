package com.amazon.interfaces;

/**
 * @author Pradeep Kumar C
 *
 */
public interface Product {
	/**
	 * Abstract method that should return the price of the selected product
	 * @return price of the product
	 */
	double getPrice();
	
	/**
	 * Abstract method that should return the title of the selected product
	 * @return title of the product
	 */
	String getProductTitle();

}

package SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
		
	public CartPage(WebDriver driver) {
		
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}


	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean verifyProductDisplay(String productName) throws InterruptedException {
		waitForElementsToVisible(productTitles);
		
	Boolean match = productTitles.stream()
			.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	return match;
	
	}
	
	public CheckoutPage goToCheckout() {
		
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

	
	
	
	

}

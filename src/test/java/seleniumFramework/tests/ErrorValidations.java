package seleniumFramework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.CheckoutPage;
import SeleniumFramework.pageobjects.ConfirmationPage;
import SeleniumFramework.pageobjects.LandingPage;
import SeleniumFramework.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

import seleniumFramework.TestComponents.BaseTest;
import seleniumFramework.TestComponents.Retry;

public class ErrorValidations extends BaseTest {  

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

	
		landingPage.loginApplication("babu11@gmail.com", "Cognizant@11");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void productErroeValidation() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("babu11@gmail.com", "Cognizant@1");
	
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Thread.sleep(3000); 
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		 
	}

}

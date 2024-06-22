package seleniumFramework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.CheckoutPage;
import SeleniumFramework.pageobjects.ConfirmationPage;
import SeleniumFramework.pageobjects.LandingPage;
import SeleniumFramework.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFramework.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	//public void submitOrder(String email, String passWord, String productName)
	public void submitOrder(HashMap<String, String> input)
			throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("passWord"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		// Thread.sleep(3000);
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		// Thread.sleep(3000);
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("babu11@gmail.com", "Cognizant@1");
		SeleniumFramework.pageobjects.OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//seleniumFramework//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}

	//@DataProvider
	//public Object[][] getData() {

		// return new Object[][] {{"babu11@gmail.com","Cognizant@1","ZARA COAT 3"},
		// {"babu11@gmail.com","Cognizant@1","ADIDAS ORIGINAL" } };
		//HashMap<String, String> map = new HashMap<String, String>();
		//map.put("email", "babu11@gmail.com");
		//map.put("passWord", "Cognizant@1");
		//map.put("product", "ZARA COAT 3");
		
		//HashMap<String, String> map1 = new HashMap<String, String>();
		//map1.put("email", "babu11@gmail.com");
		//map1.put("passWord", "Cognizant@1");
		//map1.put("product", "ADIDAS ORIGINAL");
		//return new Object[][] {{map},{map1}} ;

	//}

}

package rupsapanda.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import rupsapanda.TestComponents.BaseTest;
import rupsapanda.pageobjects.CartPage;
import rupsapanda.pageobjects.CheckOutPage;
import rupsapanda.pageobjects.ConfirmationPage;
import rupsapanda.pageobjects.LandingPage;
import rupsapanda.pageobjects.OrderPage;
import rupsapanda.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{

		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		//Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Thread.sleep(3000);
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		checkoutPage.clickOnLogout();
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() throws InterruptedException
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("pandarupsa@gmail.com", "Simun@31");
		Thread.sleep(3000);
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Thread.sleep(3000);
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
    }
	//Extent Reports - 
	@DataProvider
	public Object[][] getData() throws IOException
	{		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rupsapanda//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };		
	}
	
	

}
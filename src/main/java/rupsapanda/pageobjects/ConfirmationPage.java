package rupsapanda.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rupsapanda.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		

	}
	
	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		//CheckOutPage cp = new CheckOutPage(driver);	
		String successMsg = confirmationMessage.getText();
		System.out.println(successMsg);
		return successMsg;
	}
	
	
}
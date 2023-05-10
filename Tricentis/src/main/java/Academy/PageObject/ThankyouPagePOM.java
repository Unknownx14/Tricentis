package Academy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponent.AbstractComponent;

public class ThankyouPagePOM extends AbstractComponent {

	
WebDriver driver;
	
	public ThankyouPagePOM(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	

		
		
		//PageFactory
			@FindBy(css="div[class='title'] strong")
			WebElement confirmMessage;
			
			@FindBy(css=".order-completed-continue-button")
			WebElement orderCompleted;
			
			
			
			
			//By
			By icoRegisterByBy = By.cssSelector(".ico-register");
			
			
			//Action Methods
			
			public String getExpectedConfirmationMessage()
			{
				String expectedConfirmationMessage = "Your order has been successfully processed!";
				return expectedConfirmationMessage;
			}
			
			public String getActualConfirmationMessage()
			{
				String confirmationMessage = confirmMessage.getText();
				return confirmationMessage;
			}
			
			public Boolean continueButtonIsEnabled()
			{
				Boolean orderCompletedButton = orderCompleted.isEnabled();
				return orderCompletedButton;
			}
			
			
	
}

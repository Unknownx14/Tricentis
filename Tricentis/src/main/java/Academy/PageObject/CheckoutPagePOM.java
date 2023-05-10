package Academy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponent.AbstractComponent;

public class CheckoutPagePOM extends AbstractComponent {

	
WebDriver driver;
	
	public CheckoutPagePOM(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	

		
		
		//PageFactory
			@FindBy(css=".new-address-next-step-button")
			WebElement continueButtonAddress;
			
			@FindBy(css=".payment-method-next-step-button")
			WebElement continueButtonPaymentMethod;

			@FindBy(css=".payment-info-next-step-button")
			WebElement continueButtonPaymentInfo;
			
			@FindBy(css=".confirm-order-next-step-button")
			WebElement continueButtonConfirmOrder;
			
			
			//By
			By purchaseDetailsBy = By.cssSelector(".details");
			
			
			//Action Methods
			
			public void checkoutActions() throws InterruptedException
			{
				continueButtonAddress.click();
				Thread.sleep(1000);
				continueButtonPaymentMethod.click();
				Thread.sleep(1000);
				continueButtonPaymentInfo.click();
				Thread.sleep(1000);
				continueButtonConfirmOrder.click();
				waitForElementToAppear(purchaseDetailsBy);
				
			}
			
			
	
}

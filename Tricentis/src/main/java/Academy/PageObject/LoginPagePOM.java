package Academy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponent.AbstractComponent;

public class LoginPagePOM extends AbstractComponent {

	
WebDriver driver;
	
	public LoginPagePOM(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	//Boolean loginButtonDisplayed = driver.findElement(By.cssSelector(".ico-login")).isDisplayed();
		
		
		//PageFactory
			@FindBy(css="ul[class=top-menu] a[href*='digital']")
			WebElement digitalDownloads;
			
			@FindBy(css=".ico-logout")
			WebElement logoutLink;
			
			@FindBy(css=".ico-login")
			WebElement loginLink;

					
			
			//By
			By itemBoxBy = By.cssSelector(".item-box");
			By registerLinkBy = By.cssSelector(".ico-register");
			
			
			
			//Action Methods
	
			public void digitalDownloadsHeader()
			{
				digitalDownloads.click();
				waitForElementToAppear(itemBoxBy);
			
			}
			
			public Boolean logoutFromApplication()
			{
				logoutLink.click();
				waitForElementToAppear(registerLinkBy);
				Boolean loginButtonDisplayed = loginLink.isDisplayed();
				return loginButtonDisplayed;
			}
			
			
			
	
}

package Academy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponent.AbstractComponent;

public class LandingPagePOM extends AbstractComponent {

	
WebDriver driver;
	
	public LandingPagePOM(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
	
	//// wait for this element driver.findElement(By.cssSelector(".validation-summary-errors"));
	//String errorMessageActual = driver.findElement(By.cssSelector("div[class='validation-summary-errors'] li")).getText();
		
		
		
		//PageFactory
			@FindBy(css=".ico-login")
			WebElement loginIco;
			
			@FindBy(css="#Email")
			WebElement userEmail;
			
			@FindBy(css="#Password")
			WebElement userPassword;
			
			@FindBy(css=".login-button")
			WebElement loginButton;
			
			@FindBy(css=".header-links")
			WebElement header01;
			
			@FindBy(css="div[class='validation-summary-errors'] li")
			WebElement errorMessage;
			
			
			
			//By
			By emailBy = By.cssSelector("#Email");
			By userEmailLoggedInBy = By.cssSelector("a[href*='customer/info']");
			By errorsBy = By.cssSelector(".validation-summary-errors");
			
			
			//Action Methods
	
			public void loginApplication(String username, String password)
			{
				loginIco.click();
				waitForElementToAppear(emailBy);
				userEmail.sendKeys(username);
				userPassword.sendKeys(password);
				loginButton.click();
			}
			
			public void goTo()
			{
				driver.get("https://demowebshop.tricentis.com/");
			}
			
			public String getUsername()
			{
				String userEmailLoggedIn = header01.findElement(userEmailLoggedInBy).getText();
				System.out.println(userEmailLoggedIn);
				return userEmailLoggedIn;
			}
			
			public String errorValidationsWrongCredentials()
			{
				waitForElementToAppear(errorsBy);
				String errorMessageActual = errorMessage.getText();
				return errorMessageActual;
			}
			
	
}

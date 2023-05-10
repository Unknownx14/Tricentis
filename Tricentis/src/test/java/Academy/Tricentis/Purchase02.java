package Academy.Tricentis;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Academy.PageObject.CheckoutPagePOM;
import Academy.PageObject.DigitalDownloadsPagePOM;
import Academy.PageObject.LandingPagePOM;
import Academy.PageObject.LoginPagePOM;
import Academy.PageObject.ShoppingCartPagePOM;
import Academy.PageObject.ThankyouPagePOM;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Purchase02 {

	

	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--remote-allow-origins=*");
		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		String username = "unknownxjk@gmail.com";
		String password = "kojikurac123";
		
		//Login to the application
		LandingPagePOM lppo = new LandingPagePOM(driver);
		lppo.goTo();
		lppo.loginApplication(username, password);
		String userEmailLoggedIn = lppo.getUsername();
		
		Assert.assertEquals(userEmailLoggedIn, username);
		
		//Go to the Digital Downloads
		LoginPagePOM lipo = new LoginPagePOM(driver);
		lipo.digitalDownloadsHeader();
		
		//Choose products
		String wantedProduct01 = "3rd Album";
		int quantityProduct01 = 3;
		String wantedProduct02 = "Music 2";
		
		DigitalDownloadsPagePOM ddpo = new DigitalDownloadsPagePOM(driver);
		ddpo.addFirstProductToCart(wantedProduct01, quantityProduct01);
		ddpo.addSecondProductToCart(wantedProduct02);
		
		
		//Verify Shopping cart items
		ShoppingCartPagePOM scpo = new ShoppingCartPagePOM(driver);
		int wantedNumberOfItemsInCart = scpo.getWantedShoppingCartQty(quantityProduct01);
		int numberShoppingCartItems = scpo.getActualShoppingCartQty();
		
		Assert.assertEquals(numberShoppingCartItems, wantedNumberOfItemsInCart);	
		
		
		//Go to the ShoppingCart
		scpo.goToShoppingCart();
	/*	scpo.getFirstRowPrice(wantedProduct01);
		scpo.getSecondRowPrice(wantedProduct02);
		scpo.getFirstRowQty(wantedProduct01);
		scpo.getSeconRowQty(wantedProduct02);*/
		
		
		//Convert String to Double
		double finalSumDouble = scpo.getFinalSumDouble(wantedProduct01, wantedProduct02);
		double subTotalDouble = scpo.getSubtotalDouble();
		
		Assert.assertEquals(finalSumDouble, subTotalDouble);
		
		
		//Finish the purchase of the products
		scpo.continueShopping();
		//Here goes a method for populating billing info
		CheckoutPagePOM cppo = new CheckoutPagePOM(driver);
		cppo.checkoutActions();
		
		
		/*
		//Dropdown with the Select tag, there is a class in the Selenium called Select, and this class expects an argument(WebElement of this static dropdown)
		WebElement staticDropdown = driver.findElement(By.cssSelector("#BillingNewAddress_CountryId"));
		Select dropdown =  new Select(staticDropdown);
		dropdown.selectByVisibleText("Serbia");
		
		driver.findElement(By.cssSelector("#BillingNewAddress_City")).sendKeys("Zemun");
		driver.findElement(By.cssSelector("#BillingNewAddress_Address1")).sendKeys("Marsala Tita");
		driver.findElement(By.cssSelector("#BillingNewAddress_ZipPostalCode")).sendKeys("11080");
		driver.findElement(By.cssSelector("#BillingNewAddress_PhoneNumber")).sendKeys("0651234567"); */ 
		
		
		//Thank You page
		ThankyouPagePOM tppo = new ThankyouPagePOM(driver);
		String expectedConfirmationMessage = tppo.getExpectedConfirmationMessage();
		String confirmationMessage = tppo.getActualConfirmationMessage();
		Assert.assertEquals(confirmationMessage, expectedConfirmationMessage);
		
		Boolean orderCompletedButton = tppo.continueButtonIsEnabled();
		Assert.assertTrue(orderCompletedButton);
		
		Boolean loginButtonDisplayed = lipo.logoutFromApplication();
		Assert.assertTrue(loginButtonDisplayed);
			
		//driver.close();
		
	}

}

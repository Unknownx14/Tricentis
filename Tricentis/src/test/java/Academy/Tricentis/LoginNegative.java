package Academy.Tricentis;

import java.io.IOException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.PageObject.CheckoutPagePOM;
import Academy.PageObject.DigitalDownloadsPagePOM;
import Academy.PageObject.LandingPagePOM;
import Academy.PageObject.LoginPagePOM;
import Academy.PageObject.ShoppingCartPagePOM;
import Academy.PageObject.ThankyouPagePOM;
import Academy.TestComponents.BaseTest02;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginNegative extends BaseTest02 {

	
	
	@Test(dataProvider="getData")
	public void loginWrongCredentials(String emailDP, String passwordDP, String errorMessageDP) throws InterruptedException, IOException
	{

		
		//LandingPagePOM lppo = launchApplication();
		
		String username = emailDP;
		String password = passwordDP;
		String errorMessageExpected = errorMessageDP;
		
		//Login to the application
		lppo.loginApplication(username, password);
		String errorMessageActual = lppo.errorValidationsWrongCredentials();
		Assert.assertEquals(errorMessageActual, errorMessageExpected);
			
		
		
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"unknownxjk@gmail.com", "wrongPassword", "The credentials provided are incorrect"}, 
								{"wrongEmail@gmail.com", "kojikurac123", "No customer account found"}, 
								{"unknownxjk@gmail.com", "", "The credentials provided are incorrect"},
								{"", "kojikurac123", "No customer account found"},
								{"wrongEmail@gmail.com", "wrongPassword", "No customer account found"},
								{"", "", "No customer account found"}};
	}
	

}

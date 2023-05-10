package Academy.Tricentis;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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

public class LoginNegative03 extends BaseTest02 {

	
	
	@Test(dataProvider="getData")
	public void loginWrongCredentials(HashMap<String, String> input) throws InterruptedException, IOException
	{

		
		//LandingPagePOM lppo = launchApplication();
		
		String username = input.get("emailHM");
		String password = input.get("passwordHM");
		String errorMessageExpected = input.get("errorMessageHM");
		
		//Login to the application with wrong credentials
		lppo.loginApplication(username, password);
		String errorMessageActual = lppo.errorValidationsWrongCredentials();
		Assert.assertEquals(errorMessageActual, errorMessageExpected);
		
		
		
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		
		HashMap<String, String> hmap01 = new HashMap<String, String>();
		hmap01.put("emailHM", "unknownxjk@gmail.com");
		hmap01.put("passwordHM", "wrongPassword");
		hmap01.put("errorMessageHM", "The credentials provided are incorrect");
		
		HashMap<String, String> hmap02 = new HashMap<String, String>();
		hmap02.put("emailHM", "wrongEmail@gmail.com");
		hmap02.put("passwordHM", "kojikurac123");
		hmap02.put("errorMessageHM", "No customer account found");
		
		HashMap<String, String> hmap03 = new HashMap<String, String>();
		hmap03.put("emailHM", "unknownxjk@gmail.com");
		hmap03.put("passwordHM", "");
		hmap03.put("errorMessageHM", "The credentials provided are incorrect");
		
		HashMap<String, String> hmap04 = new HashMap<String, String>();
		hmap04.put("emailHM", "");
		hmap04.put("passwordHM", "kojikurac123");
		hmap04.put("errorMessageHM", "No customer account found");
		
		HashMap<String, String> hmap05 = new HashMap<String, String>();
		hmap05.put("emailHM", "wrongEmail@gmail.com");
		hmap05.put("passwordHM", "wrongPassword");
		hmap05.put("errorMessageHM", "No customer account found");
		
		HashMap<String, String> hmap06 = new HashMap<String, String>();
		hmap06.put("emailHM", "");
		hmap06.put("passwordHM", "");
		hmap06.put("errorMessageHM", "No customer account found");
		
		return new Object[][] {{hmap01}, {hmap02}, {hmap03}, {hmap04}, {hmap05}, {hmap06}};
		
	/*	return new Object[][] {{"unknownxjk@gmail.com", "wrongPassword", "The credentials provided are incorrect"}, 
								{"wrongEmail@gmail.com", "kojikurac123", "No customer account found"}, 
								{"unknownxjk@gmail.com", "", "The credentials provided are incorrect"},
								{"", "kojikurac123", "No customer account found"},
								{"wrongEmail@gmail.com", "wrongPassword", "No customer account found"},
								{"", "", "No customer account found"}};*/
	}
	

}

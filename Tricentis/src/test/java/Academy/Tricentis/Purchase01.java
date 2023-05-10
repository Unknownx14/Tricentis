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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Purchase01 {

	
	public static String
    removeFirstandLast(String str)
    {
 
        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);
 
        // Return the modified string
        return str;
    }
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--remote-allow-origins=*");
		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		
		String username = "unknownxjk@gmail.com";
		String password = "kojikurac123";
		
		driver.findElement(By.cssSelector(".ico-login")).click();
		
		//Explicit wait - define the object of the class
				WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
				w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Email")));
				
		driver.findElement(By.cssSelector("#Email")).sendKeys(username);
		driver.findElement(By.cssSelector("#Password")).sendKeys(password);
		driver.findElement(By.cssSelector(".login-button")).click();
		
		WebElement header01 =driver.findElement(By.cssSelector(".header-links"));
		String userEmailLoggedIn = header01.findElement(By.cssSelector("a[href*='customer/info']")).getText();
		System.out.println(userEmailLoggedIn);
		Assert.assertEquals(userEmailLoggedIn, username);
		
		driver.findElement(By.cssSelector("ul[class=top-menu] a[href*='digital']")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".item-box")));
		String wantedProduct01 = "3rd Album";
		int quantityProduct01 = 3;
		String wantedProduct02 = "Music 2";
		
		List<WebElement> allProductsList = driver.findElements(By.cssSelector(".item-box"));
		WebElement firstProduct = allProductsList.stream().filter(oneProduct -> oneProduct.findElement(By.cssSelector("h2[class='product-title'] a")).getText().equals(wantedProduct01))
		.findFirst().orElse(null);
		for (int i = 0; i < quantityProduct01; i++) {
			firstProduct.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
			w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".content")));
			w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".content")))); //This way is more quick
		}
		
		WebElement secondProduct = allProductsList.stream().filter(oneProduct -> oneProduct.findElement(By.cssSelector("h2[class='product-title'] a")).getText().equals(wantedProduct02))
				.findFirst().orElse(null);
		secondProduct.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".content")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".content")))); //This way is more quick
		
		//Verify Shopping cart items
		String shoppingCartItems = driver.findElement(By.cssSelector(".cart-qty")).getText();
		System.out.println(shoppingCartItems);
		
		String clearShoppingCartItems = removeFirstandLast(shoppingCartItems);
		System.out.println(clearShoppingCartItems);
		
		int wantedNumberOfItemsInCart = quantityProduct01 + 1;
		int numberShoppingCartItems = Integer.parseInt(clearShoppingCartItems);
		Assert.assertEquals(numberShoppingCartItems, wantedNumberOfItemsInCart);
		System.out.println(wantedNumberOfItemsInCart);
		System.out.println(numberShoppingCartItems);
		
		
		//Go to the shoppingcart
		driver.findElement(By.cssSelector(".cart-qty")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping-cart-page")));
		
		List<WebElement> allRowsShoppingCart = driver.findElements(By.cssSelector(".cart-item-row"));
		WebElement firstRow = allRowsShoppingCart.stream().filter(oneRow -> oneRow.findElement(By.cssSelector("td[class='product'] a")).getText().equals(wantedProduct01))
		.findFirst().orElse(null);
		String firstRowPrice = firstRow.findElement(By.cssSelector(".product-unit-price")).getText();
		System.out.println(firstRowPrice);
		
		WebElement secondRow = allRowsShoppingCart.stream().filter(oneRow -> oneRow.findElement(By.cssSelector("td[class='product'] a")).getText().equals(wantedProduct02))
				.findFirst().orElse(null);
				String secondRowPrice = secondRow.findElement(By.cssSelector(".product-unit-price")).getText();
				System.out.println(secondRowPrice);
				
			
		String firstRowQty = firstRow.findElement(By.cssSelector("td[class*='qty'] input")).getAttribute("value");
		System.out.println(firstRowQty);
				
		String secondRowQty = secondRow.findElement(By.cssSelector("td[class*='qty'] input")).getAttribute("value");
		System.out.println(secondRowQty);
		
		//Convert String to Double
		System.out.println("Double values after the conversion");
		double firstRowPriceDouble = Double.valueOf(firstRowPrice);
		double secondRowPriceDouble = Double.valueOf(secondRowPrice);
		double firstRowQtyDouble = Double.valueOf(firstRowQty);
		double secondRowQtyDouble = Double.valueOf(secondRowQty);
		System.out.println(firstRowPriceDouble);
		System.out.println(secondRowPriceDouble);
		System.out.println(firstRowQtyDouble);
		System.out.println(secondRowQtyDouble);
		
		double finalSumDouble = (firstRowPriceDouble * firstRowQtyDouble) + (secondRowPriceDouble * secondRowQtyDouble);
		System.out.println(finalSumDouble);
		
		String subTotal = driver.findElement(By.cssSelector(".product-price")).getText();
		double subTotalDouble = Double.valueOf(subTotal);
		System.out.println(subTotalDouble);
		
		Assert.assertEquals(finalSumDouble, subTotalDouble);
		
		
		//Finish the purchase of the products
		driver.findElement(By.cssSelector("#termsofservice")).click();
		driver.findElement(By.cssSelector("#checkout")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout-page")));
		
		/*
		//Dropdown with the Select tag, there is a class in the Selenium called Select, and this class expects an argument(WebElement of this static dropdown)
		WebElement staticDropdown = driver.findElement(By.cssSelector("#BillingNewAddress_CountryId"));
		Select dropdown =  new Select(staticDropdown);
		dropdown.selectByVisibleText("Serbia");
		
		driver.findElement(By.cssSelector("#BillingNewAddress_City")).sendKeys("Zemun");
		driver.findElement(By.cssSelector("#BillingNewAddress_Address1")).sendKeys("Marsala Tita");
		driver.findElement(By.cssSelector("#BillingNewAddress_ZipPostalCode")).sendKeys("11080");
		driver.findElement(By.cssSelector("#BillingNewAddress_PhoneNumber")).sendKeys("0651234567"); */
		driver.findElement(By.cssSelector(".new-address-next-step-button")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector(".payment-method-next-step-button")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".payment-info-next-step-button")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".confirm-order-next-step-button")).click();
		
		//Thank You page
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details")));
		String expectedConfirmationMessage = "Your order has been successfully processed!";
		String confirmationMessage = driver.findElement(By.cssSelector("div[class='title'] strong")).getText();
		Assert.assertEquals(confirmationMessage, expectedConfirmationMessage);
		
		Boolean orderCompletedButton = driver.findElement(By.cssSelector(".order-completed-continue-button")).isEnabled();
		Assert.assertTrue(orderCompletedButton);
		
		driver.findElement(By.cssSelector(".ico-logout")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ico-register")));
		Boolean loginButtonDisplayed = driver.findElement(By.cssSelector(".ico-login")).isDisplayed();
		Assert.assertTrue(loginButtonDisplayed);
		
		driver.close();
		
	}

}

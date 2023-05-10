package Academy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponent.AbstractComponent;

public class ShoppingCartPagePOM extends AbstractComponent {

	
WebDriver driver;
	
	public ShoppingCartPagePOM(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
		/*
		 driver.findElement(By.cssSelector("#termsofservice")).click();
		driver.findElement(By.cssSelector("#checkout")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout-page")));
		 */
		
		//PageFactory
			@FindBy(css=".cart-qty")
			WebElement shoppingCartQty;
			
			@FindBy(css=".cart-item-row")
			List<WebElement> allRowsShoppingCart;
			
			@FindBy(css=".product-price")
			WebElement subTotalUI;
			
			@FindBy(css="#termsofservice")
			WebElement termsOfService;
			
			@FindBy(css="#checkout")
			WebElement checkout;
			
			
			
			//By
			By shoppingCartPageBy = By.cssSelector(".shopping-cart-page");
			By checkoutPageBy = By.cssSelector(".checkout-page");
			
			
			
			//Action Methods
	
			public int getActualShoppingCartQty()
			{
				
				String shoppingCartItems = shoppingCartQty.getText();
				System.out.println(shoppingCartItems);
				String clearShoppingCartItems = removeFirstandLast(shoppingCartItems);
				System.out.println(clearShoppingCartItems);
				
				int numberShoppingCartItems = Integer.parseInt(clearShoppingCartItems);
				System.out.println(numberShoppingCartItems);
				return numberShoppingCartItems;
			}
			
			public int getWantedShoppingCartQty(int quantityProduct01)
			{
				
				int wantedNumberOfItemsInCart = quantityProduct01 + 1;
				System.out.println(wantedNumberOfItemsInCart);
				return wantedNumberOfItemsInCart;
				
			}
			
			public void goToShoppingCart()
			{
				shoppingCartQty.click();
				waitForElementToAppear(shoppingCartPageBy);
			}
			
			public List<WebElement> getListAllRowsShoppingCart()
			{
				return allRowsShoppingCart;
			}
			
			public WebElement getFirstRow(String wantedProduct01)
			{
				WebElement firstRow = getListAllRowsShoppingCart().stream().filter(oneRow -> oneRow.findElement(By.cssSelector("td[class='product'] a")).getText().equals(wantedProduct01))
						.findFirst().orElse(null);
				return firstRow;
			}
			
			public String getFirstRowPrice(String wantedProduct01)
			{
				String firstRowPrice = getFirstRow(wantedProduct01).findElement(By.cssSelector(".product-unit-price")).getText();
				System.out.println(firstRowPrice);
				return firstRowPrice;
			}
			
			public WebElement getSecondRow(String wantedProduct02)
			{
				WebElement secondRow = getListAllRowsShoppingCart().stream().filter(oneRow -> oneRow.findElement(By.cssSelector("td[class='product'] a")).getText().equals(wantedProduct02))
						.findFirst().orElse(null);
				return secondRow;
			}
			
			public String getSecondRowPrice(String wantedProduct02)
			{
				String secondRowPrice = getSecondRow(wantedProduct02).findElement(By.cssSelector(".product-unit-price")).getText();
				System.out.println(secondRowPrice);
				return secondRowPrice;
			}
			
			public String getFirstRowQty(String wantedProduct01)
			{
				String firstRowQty = getFirstRow(wantedProduct01).findElement(By.cssSelector("td[class*='qty'] input")).getAttribute("value");
				System.out.println(firstRowQty);
				return firstRowQty;
			}
			
			public String getSeconRowQty(String wantedProduct02)
			{
				String secondRowQty = getSecondRow(wantedProduct02).findElement(By.cssSelector("td[class*='qty'] input")).getAttribute("value");
				System.out.println(secondRowQty);
				return secondRowQty;
			}
			
			public double getFirstRowPriceDouble(String wantedProduct01)
			{
				double firstRowPriceDouble = Double.valueOf(getFirstRowPrice(wantedProduct01));
				System.out.println(firstRowPriceDouble);
				return firstRowPriceDouble;
			}
			
			public double getSecondRowPriceDouble(String wantedProduct02)
			{
				double secondRowPriceDouble = Double.valueOf(getSecondRowPrice(wantedProduct02));
				System.out.println(secondRowPriceDouble);
				return secondRowPriceDouble;
			}
			
			public double getFirstRowQtyDouble(String wantedProduct01)
			{
				double firstRowQtyDouble = Double.valueOf(getFirstRowQty(wantedProduct01));
				System.out.println(firstRowQtyDouble);
				return firstRowQtyDouble;
			}
			
			public double getSecondRpwQtyDouble(String wantedProduct02)
			{
				double secondRowQtyDouble = Double.valueOf(getSeconRowQty(wantedProduct02));
				System.out.println(secondRowQtyDouble);
				return secondRowQtyDouble;
			}
	
			public double getFinalSumDouble(String wantedProduct01, String wantedProduct02)
			{
				double finalSumDouble = (getFirstRowPriceDouble(wantedProduct01) * getFirstRowQtyDouble(wantedProduct01)) + (getSecondRowPriceDouble(wantedProduct02) * getSecondRpwQtyDouble(wantedProduct02));
				System.out.println(finalSumDouble);
				return finalSumDouble;
			}
			
			public double getSubtotalDouble()
			{
				String subTotal = subTotalUI.getText();
				double subTotalDouble = Double.valueOf(subTotal);
				System.out.println(subTotalDouble);
				return subTotalDouble;
			}
			
			public void continueShopping()
			{
				termsOfService.click();
				checkout.click();
				waitForElementToAppear(checkoutPageBy);
			}
			
			
			
}

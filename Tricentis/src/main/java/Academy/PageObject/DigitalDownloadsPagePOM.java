package Academy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Academy.AbstractComponent.AbstractComponent;

public class DigitalDownloadsPagePOM extends AbstractComponent {

	
WebDriver driver;
	
	public DigitalDownloadsPagePOM(WebDriver driver)
	{
		super(driver); //This way we connect a child to a parent
		this.driver = driver; //The left driver is from this PO class, the right driver is from StandAloneTestAGAIN156
		PageFactory.initElements(driver, this); //The method for the PageFactory to knows about the driver
	}
	
	
		
		
		//PageFactory
			@FindBy(css=".item-box")
			List<WebElement> allProductsList;
			
			@FindBy(css=".content")
			WebElement content;
			
			
			
			
			
			//By
			By contentBy = By.cssSelector(".content");
			
			
			
			//Action Methods
	
			public List<WebElement> getAllProductsList()
			{
				return allProductsList;
			}
			
			public WebElement getFirstProduct(String wantedProduct01)
			{
				WebElement firstProduct = getAllProductsList().stream().filter(oneProduct -> oneProduct.findElement(By.cssSelector("h2[class='product-title'] a")).getText().equals(wantedProduct01))
						.findFirst().orElse(null);
				return firstProduct;
			}
			
			public void addFirstProductToCart(String wantedProduct01, int quantityProduct01)
			{
				WebElement firstProduct = getFirstProduct(wantedProduct01);
				
				for (int i = 0; i < quantityProduct01; i++) {
					firstProduct.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
					waitForElementToAppear(contentBy);
					waitForElementToDisappear(content);
					
				}
				
			}
			
			public WebElement getSecondProduct(String wantedProduct02)
			{
				WebElement secondProduct = allProductsList.stream().filter(oneProduct -> oneProduct.findElement(By.cssSelector("h2[class='product-title'] a")).getText().equals(wantedProduct02))
						.findFirst().orElse(null);
				return secondProduct;
			}
			
			public void addSecondProductToCart(String wantedProduct02)
			{
				WebElement secondProduct = getFirstProduct(wantedProduct02);
					secondProduct.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
					waitForElementToAppear(contentBy);
					waitForElementToDisappear(content);
					
				
				
			}
			
			
			
	
}

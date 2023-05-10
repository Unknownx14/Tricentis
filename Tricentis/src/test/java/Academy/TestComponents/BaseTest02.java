package Academy.TestComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Academy.PageObject.LandingPagePOM;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest02 {

	
	//Here we have done the following - Initializing the WebDriver driver is moved here, Properties class is used for .properties file, moved all common things
		//at the end of the IfElse loop, made sure that WebDriver driver is stated at the beginning
		
		public WebDriver driver;
		public LandingPagePOM lppo;
	
	public WebDriver initializeDriver() throws IOException
	{
				//Properties class is for using GlobalData.properties file
				Properties prop = new Properties();
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Academy\\Resources\\GlobalData.properties");
				prop.load(fis);
				String browserName = prop.getProperty("browser");
				
				if(browserName.equals("chrome"))
				{
					ChromeOptions options = new ChromeOptions(); 
					options.addArguments("--remote-allow-origins=*");
				WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
				 driver = new ChromeDriver(options);
				
				}
				else if(browserName.equals("firefox"))
				{
					System.setProperty("webdriver.gecko.driver", "C:\\JK\\geckodriver-v0.31.0-win64\\geckodriver.exe");
					 driver = new FirefoxDriver();
					
				}
				else if(browserName.equals("edge"))
				{
					System.setProperty("webdriver.edge.driver", "C:\\JK\\edgedriver_win64\\msedgedriver.exe");
					 driver = new EdgeDriver();
					
				}
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				return driver;
				
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPagePOM launchApplication() throws IOException
	{
		 driver =  initializeDriver();
		  lppo = new LandingPagePOM(driver);
		  lppo.goTo();
		  return lppo;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.close();
	}
	
	
}

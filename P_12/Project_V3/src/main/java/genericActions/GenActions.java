package genericActions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import wixLog.Log;

public class GenActions extends Log
{
  public WebDriver driver;
	
	public static int PAGE_LOAD_TIME_OUT = 60;
	public static int IMPLICITY_TIME_OUT = 30;
	
	public void lanchBrowser(String browserName,String URL) throws IOException
	{
		
		switch(browserName)
		{
			case "Chrome" : log.info("**********************************Initiating The Chrome Browser**********************************");
							log.info(browserName+" Browser Is Found");
							WebDriverManager.chromedriver().setup();
							ChromeOptions co = new ChromeOptions();
							co.addArguments("start-maximized");
							driver = new ChromeDriver(co);
							
						    break;
			case "Firefox" :log.info(browserName+" Browser Is Found");
							log.info("**********************************Initiating The Firefox Browser**********************************");
							WebDriverManager.firefoxdriver().setup();
							driver = new FirefoxDriver();
							driver.manage().window().maximize();
							break;
			default: log.error("Browser Is In Out Of Scope");
		}
		if(driver!=null)
		{
		log.info("Browser successfully Stared..");
		
		log.info("Deleting All The Coockies");
		driver.manage().deleteAllCookies();
		
		log.info("Setting PageLoadTimeOut To "+PAGE_LOAD_TIME_OUT);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIME_OUT,TimeUnit.SECONDS);
		
		log.info("Setting ImplicityTimeOut To "+IMPLICITY_TIME_OUT);
		driver.manage().timeouts().implicitlyWait(IMPLICITY_TIME_OUT,TimeUnit.SECONDS);
		
		log.info("Navigating To "+URL);
		driver.get(URL);
		log.info("Navigated To "+URL+" Successfully");
		}
	}
	
	public void closeBrowser()
	{
		log.info("*****************************************************Closing The Browser*************************************");
		driver.close();
		log.info("Browser Closed Successfully");
	}
	public WebElement UIElement(String Location)
	{
		return driver.findElement(By.xpath(Location));
	}
	public boolean VisibiltyOfElementLocated(WebElement element)
	{
		return element.isDisplayed();
		
	}
	public boolean ElementIsEnabled(WebElement element)
	{
		return element.isEnabled();	
	}
	
	public void HighLightElement(WebElement element,WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px dashed red;');",element);
		Thread.sleep(1000);
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px dashed lime;');",element);
		js.executeScript("arguments[0].setAttribute('style', '');",element);
	}
	
	public void input(String ElementName,String Location,String TestData) throws InterruptedException
	{
		log.info("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log.info("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log.info("Clearing The element ["+ElementName+"] at Location ="+Location);
				UIElement(Location).clear();
				
				log.info("Entering The Data ["+TestData+"] In The Element ["+ElementName+"] at Location ="+Location);
				UIElement(Location).sendKeys(TestData);
				log.info("Succussfully Data Is Entered For An Element ["+ElementName+"] at Location ="+Location+" With Test Data:"+TestData);
				
			}	
			else
			{
				log.error("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log.error("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}
		
	}
	public void click(String ElementName,String Location) throws InterruptedException
	{
		log.info("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log.info("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log.info("Clicking An Element ["+ElementName+"] at Location ="+Location);
				UIElement(Location).click();
				log.info("Succussfully Element Is Clicked ["+ElementName+"] at Location ="+Location);
			}
			else
			{
				log.error("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log.error("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}

	}
	public void waitForElement(int sec) throws InterruptedException
	{
		
		Thread.sleep(sec);
		log.info("Spent "+(sec/1000)+" Seconds");
	}

	

}

package Generic.pack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import logs.pack.ExtentLogger;
import logs.pack.Log;

public class GenericType extends Log
{

	public static WebDriver driver;
	public ExtentLogger logger;
	public static int PAGE_LOAD_TIME_OUT = 240;
	public static int IMPLICITY_TIME_OUT = 30;
	public static SoftAssert asert;
	
	public GenericType()
	{
		super();
		
	}
	
	public void LanchBrowser(String browserName,String URL) throws IOException
	{ 
		asert = new SoftAssert();
		
		switch(browserName)
		{
			case "Chrome" : log.info("*************************************************************************************************************************************************************");
							log.info("**********************************************************INITIALIZING CHROME BROWSER************************************************************************");
							log.info("*************************************************************************************************************************************************************");
							WebDriverManager.chromedriver().setup();
							ChromeOptions co = new ChromeOptions();
							co.addArguments("start-maximized");
							//co.addArguments("headless");
							driver = new ChromeDriver(co);
							
						    break;
			case "Firefox" :log.info("*************************************************************************************************************************************************************");
							log.info("**********************************************************INITIALIZING FIREFOX BROWSER************************************************************************");
							log.info("*************************************************************************************************************************************************************");
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
	
	public void CloseBrowser()
	{
		log.info("*************************************************************************************************************************************************************");
		log.info("**********************************************************-C-L-O-S-E-****************************************************************************************");
		log.info("*************************************************************************************************************************************************************");
		log.info("**********************************************************CLOSING BROWSER************************************************************************************");
		log.info("*************************************************************************************************************************************************************");
		driver.close();
		log.info("*****************************************************BROWSER CLOSED SUCCESSFULLY*****************************************************************************");
		log.info("*************************************************************************************************************************************************************");
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
				JavascriptExecutor js=((JavascriptExecutor)driver);
				js.executeScript("arguments[0].value='';",UIElement(Location));
				
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
	public void JSclick(String ElementName,String Location) throws InterruptedException
	{
		log.info("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log.info("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log.info("Clicking An Element ["+ElementName+"] at Location ="+Location);
				JavascriptExecutor js=((JavascriptExecutor)driver);
				js.executeScript("arguments[0].click();",UIElement(Location));
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

	public void doubleClick(String ElementName,String Location) throws InterruptedException
	{
		log.info("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log.info("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log.info("Double Clicking An Element ["+ElementName+"] at Location ="+Location);
				new Actions(driver).doubleClick(UIElement(Location)).build().perform();
				log.info("Succussfully Element Is Double Clicked ["+ElementName+"] at Location ="+Location);
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
	public void Sleep(int sec) throws InterruptedException
	{
		
		Thread.sleep(sec);
		log.info("Spent "+(sec/1000)+" Seconds");
	}

	public void verify(String ElementName,String Location) throws InterruptedException
	{
		log.info("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log.info("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
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
	
	public List<WebElement> elementCount(String xpath,String name)
	{
		log.info("Web Elements Found:"+driver.findElements(By.xpath(xpath)).size()+name);
		return driver.findElements(By.xpath(xpath));
	}
	
	public void specificWait(String xpath,int timeInSeconds)
	{
		new WebDriverWait(driver,timeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void select(String ElementName,String Location,String data) throws InterruptedException
	{
		log.info("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log.info("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log.info("Selecting An Element ["+ElementName+"] with Option "+data+" at Location ="+Location);
				new Select(UIElement(Location)).selectByVisibleText(data);
				log.info("Succussfully Element Is Selected ["+ElementName+"] with Option "+data+" at Location ="+Location);
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
	public void dropAndDrop(String ElementName,String Location,String Location1) throws InterruptedException
	{
		log.info("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log.info("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log.info("Dragging An Element ["+ElementName+"] From "+Location+" To ="+Location1);
				new Actions(driver).dragAndDrop(UIElement(Location), UIElement(Location1)).build().perform();
				log.info("Succussfully Element Is Dragged ["+ElementName+"] From "+Location+" To ="+Location1);
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
	public boolean dataValidation(String Location,String data)
	{
		String response = UIElement(Location).getText();
		
		log.info("Validating The Data ["+data+"] Is Available At Location ["+Location+" ]");
		
		if(response.contains(data))
		{
			log.info("Data ["+data+"] Matched...with ["+response+"] at Location ["+Location+"]");
			return true;
		}
		else
		{
			log.info("Data ["+data+"] did not Match...with ["+response+"] at Location ["+Location+"]");
			return false;
		}
		
	}
	public boolean dataValidation1(String Location,String data)
	{
		String response = UIElement(Location).getAttribute("value");
		
		log.info("Validating The Data ["+data+"] Is Available At Location ["+Location+" ]");
		
		if(response.contains(data))
		{
			log.info("Data ["+data+"] Matched...with ["+response+"] at Location ["+Location+"]");
			return true;
		}
		else
		{
			log.info("Data ["+data+"] did not Match...with ["+response+"] at Location ["+Location+"]");
			return false;
		}
		
	}
	
	public void attachTheImage(String Location) throws InterruptedException, AWTException
	{
		StringSelection stringSelection = new StringSelection(System.getProperty("user.dir")+"\\"+Location);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        
        Robot robot=new Robot();
		
		log.info("Inputting The Location["+System.getProperty("user.dir")+"\\"+Location+"]In Window");
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Release key
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(5000);
		log.info("Clicking On Open in Window");
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	public void softAssert(String Location,String data) throws IOException
	{
		
		String response = UIElement(Location).getText();
		
		log.info("Validating The Data ["+data+"] Is Available At Location ["+Location+" ]");
		
		asert.assertEquals(response, data);
		if(response.contains(data))
		{
			log.info("Data ["+data+"] Matched...with ["+response+"] at Location ["+Location+"]");
		}
		else
		{
			log.info("Data ["+data+"] did not Match...with ["+response+"] at Location ["+Location+"]");
			captureScreenShot();
		}
	}
	
	public void assertClose()
	{
		asert.assertAll();
	}
	
	public void captureScreenShot() throws IOException
	{
				//Get system Default date
				Date d=new Date();
				//Create simple date format
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/");
				//Convert default date using simple date format
				String time=sdf.format(d);
				
				
				File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File("Reports\\ScreenShots\\"+driver.getCurrentUrl()+".png"));
	}
	
}

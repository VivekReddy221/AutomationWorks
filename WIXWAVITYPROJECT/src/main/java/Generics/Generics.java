package Generics;

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
import report.logs.Logs;

public class Generics
{
	public static WebDriver driver;
	public static Logs log;
	public static int PAGE_LOAD_TIME_OUT = 240;
	public static int IMPLICITY_TIME_OUT = 30;
	public static SoftAssert asert;
	
	
	
	public void LanchBrowser(String browserName,String URL) throws IOException
	{ 
		asert = new SoftAssert();
		
		log = new Logs();
		
		switch(browserName)
		{
			case "Chrome" : log._INFO("*************************************************************************************************************************************************************");
							log._INFO("**********************************************************INITIALIZING CHROME BROWSER************************************************************************");
							log._INFO("*************************************************************************************************************************************************************");
							WebDriverManager.chromedriver().setup();
							ChromeOptions co = new ChromeOptions();
							co.addArguments("start-maximized");
							//co.addArguments("headless");
							driver = new ChromeDriver(co);
							
						    break;
			case "Firefox" :log._INFO("*************************************************************************************************************************************************************");
							log._INFO("**********************************************************INITIALIZING FIREFOX BROWSER************************************************************************");
							log._INFO("*************************************************************************************************************************************************************");
							WebDriverManager.firefoxdriver().setup();
							driver = new FirefoxDriver();
							driver.manage().window().maximize();
							break;
			default: log._ERROR("Browser Is In Out Of Scope");
		}
		if(driver!=null)
		{
		log._INFO("Browser successfully Stared..");
		
		log._INFO("Deleting All The Coockies");
		driver.manage().deleteAllCookies();
		
		log._INFO("Setting PageLoadTimeOut To "+PAGE_LOAD_TIME_OUT);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIME_OUT,TimeUnit.SECONDS);
		
		log._INFO("Setting ImplicityTimeOut To "+IMPLICITY_TIME_OUT);
		driver.manage().timeouts().implicitlyWait(IMPLICITY_TIME_OUT,TimeUnit.SECONDS);
		
		log._INFO("Navigating To "+URL);
		driver.get(URL);
		log._INFO("Navigated To "+URL+" Successfully");
		}
	}
	
	public void CloseBrowser()
	{
		log._INFO("*************************************************************************************************************************************************************");
		log._INFO("**********************************************************-C-L-O-S-E-****************************************************************************************");
		log._INFO("*************************************************************************************************************************************************************");
		log._INFO("**********************************************************CLOSING BROWSER************************************************************************************");
		log._INFO("*************************************************************************************************************************************************************");
		driver.close();
		log._INFO("*****************************************************BROWSER CLOSED SUCCESSFULLY*****************************************************************************");
		log._INFO("*************************************************************************************************************************************************************");
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
		
		log._INFO("Verifying The Element ["+ElementName+"] at Location ="+Location);
		
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			
			log._INFO("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				
				HighLightElement(UIElement(Location),driver);
				
				log._INFO("Clearing The element ["+ElementName+"] at Location ="+Location);
				UIElement(Location).clear();
				JavascriptExecutor js=((JavascriptExecutor)driver);
				js.executeScript("arguments[0].value='';",UIElement(Location));
				
				log._INFO("Entering The Data ["+TestData+"] In The Element ["+ElementName+"] at Location ="+Location);
				UIElement(Location).sendKeys(TestData);
				log._INFO("Succussfully Data Is Entered For An Element ["+ElementName+"] at Location ="+Location+" With Test Data:"+TestData);
			}	
			else
			{
	
				log._ERROR("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log._ERROR("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}
	}
	public void  click(String ElementName,String Location) throws InterruptedException
	{
		log._INFO("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log._INFO("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log._INFO("Clicking An Element ["+ElementName+"] at Location ="+Location);
				UIElement(Location).click();
				log._INFO("Succussfully Element Is Clicked ["+ElementName+"] at Location ="+Location);
			}
			else
			{
				log._ERROR("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{ 
			log._ERROR("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}
		
		
	}
	public void JSclick(String ElementName,String Location) throws InterruptedException
	{
		log._INFO("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log._INFO("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log._INFO("Clicking An Element ["+ElementName+"] at Location ="+Location);
				JavascriptExecutor js=((JavascriptExecutor)driver);
				js.executeScript("arguments[0].click();",UIElement(Location));
				log._INFO("Succussfully Element Is Clicked ["+ElementName+"] at Location ="+Location);
			}
			else
			{
				log._ERROR("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log._ERROR("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}

	}

	public void doubleClick(String ElementName,String Location) throws InterruptedException
	{
		log._INFO("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log._INFO("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log._INFO("Double Clicking An Element ["+ElementName+"] at Location ="+Location);
				new Actions(driver).doubleClick(UIElement(Location)).build().perform();
				log._INFO("Succussfully Element Is Double Clicked ["+ElementName+"] at Location ="+Location);
			}
			else
			{
				log._ERROR("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log._ERROR("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}

	}
	public void Sleep(int sec) throws InterruptedException
	{
		
		Thread.sleep(sec);
		log._INFO("Spent "+(sec/1000)+" Seconds");
	}

	public void verify(String ElementName,String Location) throws InterruptedException
	{
		log._INFO("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log._INFO("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
			}
			else
			{
				log._ERROR("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log._ERROR("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}

	}
	
	public List<WebElement> elementCount(String xpath,String name)
	{
		log._INFO("Web Elements Found:"+driver.findElements(By.xpath(xpath)).size()+name);
		return driver.findElements(By.xpath(xpath));
	}
	
	public void specificWait(String xpath,int timeInSeconds)
	{
		new WebDriverWait(driver,timeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void select(String ElementName,String Location,String data) throws InterruptedException
	{
		log._INFO("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log._INFO("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log._INFO("Selecting An Element ["+ElementName+"] with Option "+data+" at Location ="+Location);
				new Select(UIElement(Location)).selectByVisibleText(data);
				log._INFO("Succussfully Element Is Selected ["+ElementName+"] with Option "+data+" at Location ="+Location);
			}
			else
			{
				log._ERROR("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log._ERROR("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}

	}
	public void dropAndDrop(String ElementName,String Location,String Location1) throws InterruptedException
	{
		log._INFO("Verifying The Element ["+ElementName+"] at Location ="+Location);
		if(VisibiltyOfElementLocated(UIElement(Location)) == true)
		{
			log._INFO("Element ["+ElementName+"] Is Found"+" at Location ="+Location);
			if(ElementIsEnabled(UIElement(Location))== true)
			{
				HighLightElement(UIElement(Location),driver);
				
				log._INFO("Dragging An Element ["+ElementName+"] From "+Location+" To ="+Location1);
				new Actions(driver).dragAndDrop(UIElement(Location), UIElement(Location1)).build().perform();
				log._INFO("Succussfully Element Is Dragged ["+ElementName+"] From "+Location+" To ="+Location1);
			}
			else
			{
				log._ERROR("Element ["+ElementName+"] Is In Disable Mode at Location ="+Location);
			}
		}
		else
		{
			log._ERROR("Element ["+ElementName+"] Is Not Found at Location ="+Location);
		}
}
	public boolean dataValidation(String Location,String data)
	{
		String response = UIElement(Location).getText();
		
		log._INFO("Validating The Data ["+data+"] Is Available At Location ["+Location+" ]");
		
		if(response.contains(data))
		{
			log._INFO("Data ["+data+"] Matched...with ["+response+"] at Location ["+Location+"]");
			return true;
		}
		else
		{
			log._INFO("Data ["+data+"] did not Match...with ["+response+"] at Location ["+Location+"]");
			return false;
		}
		
	}
	public boolean dataValidation1(String Location,String data)
	{
		String response = UIElement(Location).getAttribute("value");
		
		log._INFO("Validating The Data ["+data+"] Is Available At Location ["+Location+" ]");
		
		if(response.contains(data))
		{
			log._INFO("Data ["+data+"] Matched...with ["+response+"] at Location ["+Location+"]");
			return true;
		}
		else
		{
			log._INFO("Data ["+data+"] did not Match...with ["+response+"] at Location ["+Location+"]");
			return false;
		}
		
	}
	
	public void attachTheImage(String Location) throws InterruptedException, AWTException
	{
		StringSelection stringSelection = new StringSelection(System.getProperty("user.dir")+"\\"+Location);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        
        Robot robot=new Robot();
		
		log._INFO("Inputting The Location["+System.getProperty("user.dir")+"\\"+Location+"]In Window");
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Release key
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(5000);
		log._INFO("Clicking On Open in Window");
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	public void softAssert(String Location,String data) throws IOException
	{
		
		String response = UIElement(Location).getText();
		
		log._INFO("Validating The Data ["+data+"] Is Available At Location ["+Location+" ]");
		
		//asert.assertEquals(response, data);
		if(response.contains(data))
		{
			log._INFO("Data ["+data+"] Matched...with ["+response+"] at Location ["+Location+"]");
		}
		else
		{
			log._INFO("Data ["+data+"] did not Match...with ["+response+"] at Location ["+Location+"]");
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

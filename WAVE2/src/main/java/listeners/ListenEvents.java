package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Generics.Generics;
import excel.utils.ExcelReadPack;
import report.logs.Logs;
import specifics.SpecificType;
import xpath.hub.XpathHub;

public class ListenEvents implements ITestListener
{
	static Logs log;
	static Generics gen;
	static SpecificType spec;
	static XpathHub xpath;
	static ExcelReadPack ob;

	@Override
	public void onStart(ITestContext context) 
	{
		log = new Logs();
		gen = new Generics();
		spec = new SpecificType();
		xpath = new XpathHub();
		ob = new ExcelReadPack();
		
		log.extentInitailizer(); // Extent Report Initializer
		
		try
		{
			String workbooks[] = {"TestData.xlsx","ConfigFile.xlsx"}; // All Workbooks Initializations
			
			ob.OpenAWBooks(workbooks);
		}
		catch (IOException e1) 
		{
			
			e1.printStackTrace();
		}
		try
		{
			xpath.XpathHubStore("WebUIMaps.xlsx","WebUIMaps"); // XPath Initializer
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		
		try {
			gen.LanchBrowser("Chrome","http://tenant42.wavity.info");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		
		gen.CloseBrowser();
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		
		gen.CloseBrowser();
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		
		gen.CloseBrowser();
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		try 
		{
			ob.closeAllBooks();
			xpath.closeXpathBook();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		log.storeInReport();
	}
	
}

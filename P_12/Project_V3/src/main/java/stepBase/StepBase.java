package stepBase;

import java.io.IOException;

import Commons.Commonsex;
import excelUtil.ExcelUtil;
import genericActions.GenActions;
import wixLog.Log;


public class StepBase extends Log
{
	String Location;
	GenActions genAction;
	
	public static String BROWSERTYPE="Chrome";	
	public static String URL="http://test.wavity.io";
	public static String USERNAME="swethak@wavity.com";
	public static String PASSWORD="Wavity@6";
	
	public StepBase()
	{
		super();
	}
	
	public void TestCaseName(String TestcaseNameid, String TestCaseDescriptiom,String TestingType)
	{
		//new Log().CreateTest(TestcaseNameid, TestCaseDescriptiom, TestingType);
		log.info("*****************************************************"+TestcaseNameid+"*************************************");
		log.info("*****************************************************"+TestCaseDescriptiom+"*************************************");
	}
	
	public void Step(String Description,String Keyword,String ElementName) throws InterruptedException, IOException
	{
		mainExe(Description,Keyword,ElementName,"Click");
	}
	
	public void Step(String Description,String Keyword,String ElementName, String TestData) throws InterruptedException, IOException
	{
		mainExe(Description,Keyword,ElementName,TestData);
	}
	
	public void Step(String Description,String Keyword) throws InterruptedException, IOException
	{
		mainExe(Description,Keyword,"userName","common");
	}
	
	
	// Main Executor 
	public void mainExe(String Description,String Keyword,String ElementName, String TestData) throws InterruptedException, IOException
	{
		log.info("=====> "+Description);
		Location = new ExcelUtil().xpathSender(ElementName);
		
			switch(Keyword)
			{
			case "input"   : genAction.click(ElementName, TestData);
						  break;
			case "click"   : genAction.input(ElementName, ElementName, TestData);
				          break;
			case "login" : new Commonsex().login();
							break;
			case "logout" : new Commonsex().logout();
			break;
		    default: log.error("Action Is In Out Of Scope: "+Keyword);
			}
		
			
	}
}

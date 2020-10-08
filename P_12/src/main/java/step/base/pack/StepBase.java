package step.base.pack;

import java.awt.AWTException;
import java.io.IOException;

import Generic.pack.GenericType;
import Specifics.pack.SpecificType;
import logs.pack.ExtentLogger;
import logs.pack.Log;
import utilities.hub.pack.StringClsUtil;
import xpath.hub.pack.XpathHub;

public class StepBase extends Log
{
	GenericType gen;
	String Location,Location1;
	SpecificType spec;
	String TestData1[][]={ {"testData","NO"}};
	
	public void TestCaseName(String testCaseId,String TestDesc)
	{
		log.info("**********************************************************-S-T-A-R-T-****************************************************************************************");
		log.info("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log.info("::::::::::::::::::::::::::::::::::"+testCaseId+"-"+TestDesc+"::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log.info("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public void Step(String Des,String action) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,"YES","NO","NO","NO","click",TestData1);
	}
	public void Step(String Des,String action,String elementName) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,elementName,"NO","NO","NO","click",TestData1);
	}
	public void Step(String Des,String action,String elementName,String TestData) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,elementName,"NO","NO","NO",TestData,TestData1);
	}

	public void Step(String Des,String action,String elementName,String elementName1,String TestData) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,action,elementName,elementName1,"NO","NO",TestData,TestData1);
	}
	
	public void keyword(String Des, String keyword, String KeywordName,String TestData[][]) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,"NO","NO","NO",keyword,KeywordName,"NO",TestData);
	}

	public void keyword(String Des, String keyword, String KeywordName) throws InterruptedException, IOException, AWTException
	{
		MainLooper(Des,"NO","NO","NO",keyword,KeywordName,"NO",TestData1);
	}
	void MainLooper(String Des,String action,String elementName,String elementName1,String keyword, String KeywordName,String TestData,String testData[][]) throws InterruptedException, IOException, AWTException
	{
		log.info(":::::====>> "+Des);
		gen = new GenericType();
		spec = new SpecificType();
		
		if(keyword=="NO")
		{
			if(elementName1!="YES")
			{
			if(action!="dragAndDrop")
			{
			if(new XpathHub().xpathGetter(elementName).contains("[$]"))
			{
				Location = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter(elementName),TestData);
			}
			else
			{
				Location = new XpathHub().xpathGetter(elementName);
			}
			}
			else
			{
					Location = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter(elementName),TestData);
					Location1 = new XpathHub().xpathGetter(elementName1);
			}
			}
			else
			{
				Location = elementName;
			}
		}
		else
		{
			log.info("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
			log.info("**************************************"+"Keyword"+"-"+KeywordName+" Execution Started**********************************");
			log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			
		}
		if(action!=null && elementName!=null && testData!=null && keyword=="NO" )
		{
			log.info("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
			switch(action)
			{
			case "input": gen.input(elementName, Location, TestData);
						 break;
			case "click": gen.click(elementName, Location);
						 break;
			case "verify": gen.verify(elementName, Location);
			 			 break;
			case "select": gen.select(elementName,Location, TestData);
						 break;
			case "dragAndDrop":gen.dropAndDrop(elementName, Location, Location1);
						 break;
			case "attachTheImage": gen.attachTheImage(TestData);
						break;
			case "softAssert" : gen.softAssert(Location, TestData);
						break;
			default: log.error("Given Action Is In Out Of Scope");
			}
		}
		else
		{
			switch(KeywordName)
			{
			case "login": spec.login(testData);
						 break;
			case "acceptCookie":spec.acceptCookie();
						 break;
			case "logout": spec.logout();
						 break;
			case "createAnApp":spec.createAnApp(testData);
						 break;
			case "addControls_setProprties":spec.addControls_setProprties(testData);
						 break;
			case "addRecords": spec.addRecords(testData);
						 break;
			case "search_Delete_App": spec.search_Delete_App(testData);
						 break;
			case "dataValidationInLISTpage": spec.dataValidationInLISTpage(testData);
						 break;
			case "dataValidationInLINE_EDITpage": spec.dataValidationInLINE_EDITpage(testData);
						 break;
			case "dataValidationNORMALEDITpage" : spec.dataValidationNORMALEDITpage(testData);
						 break;
			case "verifyExistingRecords" : spec.verifyExistingRecords(testData);
						 break;
			default: log.error("Given Specific Keyword Is In Out Of Scope");
			}
		}
		log.info("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

}

package TestCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import Generics.Generics;
import StepsBase.StepBase;
import excel.utils.ExcelReadPack;
import report.logs.Logs;

public class Login_Logout extends StepBase
{
	
	String testData[][],
	appCreationData[][],
	addControlsData[][],
	loginData[][],
	RecordsData[][];
	
	@Test(priority = 1,enabled = false)
	public void Login_Logout() throws IOException, InterruptedException, AWTException
	{
		TestCaseName("WAVE-TC-01","Verifying App & Record Creation");
		
		loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		testData = new ExcelReadPack().testDataReaderInCellRange("appsData",3,3,4);
		keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData);
		
		appCreationData = new ExcelReadPack().testDataReaderInCellRange("appsData",3,2,5);
		keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
		
		addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("appsData",8,11,2,10);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
		
		testData=  new ExcelReadPack().testDataReaderInRow_CellRange("appsData",23,25,2,5);
		keyword("Creating The Records For Text Based Application","Keyword","addRecords",testData);
		
		keyword("Logging Out From The Application","Keyword","logout");
	}
	

	@Test(priority = 2,enabled = false)
	public void Login() throws IOException, InterruptedException, AWTException
	{
		
		TestCaseName("WAVE-TC-02","Creating An App With All The Controls");
		
		loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		testData = new ExcelReadPack().testDataReaderInCellRange("appsData",3,3,4);
		keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData);
		
		appCreationData = new ExcelReadPack().testDataReaderInCellRange("appsData",3,2,5);
		keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
		
		addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("appsData",8,34,2,5);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
		
		keyword("Activating The App","keyword","activateApp");
		
		keyword("Logging Out From The Application","Keyword","logout");
	}
	
	@Test(priority = 3,enabled = false)
	public void CreateAnAppWithTextControls() throws IOException, InterruptedException, AWTException
	{
		
		TestCaseName("WAVE-APPS-TC-01","Creating An App With Text Control");
		
		loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithTextNumber",3,3,4);
		keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
		
		appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithTextNumber",3,2,5);
		keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
		
		addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithTextNumber",8,13,2,5);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
		
		keyword("Activating The App","keyword","activateApp");
		
		RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithTextNumber",8,13,6,12);
		keyword("Adding Records To The Application","keyword","addRecords",RecordsData);
		
		keyword("Logging Out From The Application","Keyword","logout");
	}
	@Test(priority = 4)
	public void CreateAnAppWithNumbersControl() throws IOException, InterruptedException, AWTException
	{
		
		TestCaseName("WAVE-APPS-TC-01","Creating An App With Numbers Control");
		
		loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,5);
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		testData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithNumbers",3,3,4);
		keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData); 
		
		appCreationData = new ExcelReadPack().testDataReaderInCellRange("CreateAnAppWithNumbers",3,2,5);
		keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
		
		addControlsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithNumbers",8,11,2,5);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData); 
		
		keyword("Activating The App","keyword","activateApp");
		
		RecordsData = new ExcelReadPack().testDataReaderInRow_CellRange("CreateAnAppWithNumbers",8,11,6,12);
		keyword("Adding Records To The Application","keyword","addRecords",RecordsData);
		
		keyword("Logging Out From The Application","Keyword","logout");
	}

}

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
	
	@Test(priority = 1,enabled = true)
	public void Login_Logout() throws IOException, InterruptedException, AWTException
	{
		TestCaseName("WAVE-TC-01","Verifying Login Functionality");
		
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
	

	@Test(priority = 2)
	public void Login() throws IOException, InterruptedException, AWTException
	{
		
		TestCaseName("WAVE-TC-02","Verifying Login Page Objects");
		Step("Verfiying The Login Card","verify","loginCard");
		Step("Verfiying The Wavity Logo","verify","logo");
	}
	
	

}

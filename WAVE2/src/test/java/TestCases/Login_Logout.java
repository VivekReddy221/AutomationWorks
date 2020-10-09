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
	String loginData[][];
	
	@Test(priority = 1)
	public void Login_Logout() throws IOException, InterruptedException, AWTException
	{
		loginData = new ExcelReadPack().testDataReaderInCellRange("Config",3,3,4);
		
		TestCaseName("WAVE-TC-01","Verifying Login & Logout Functionality");
		
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		keyword("Logging Out From The Application","Keyword","logout"); 
	}
	

	@Test(priority = 2)
	public void Login() throws IOException, InterruptedException, AWTException
	{
		
		TestCaseName("WAVE-TC-02","Verifying Login Page Objects");
		keyword("Logging Out From The Application","Keyword","logout"); 
	}
	
	

}

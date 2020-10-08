package wix.test.cases;

import java.io.IOException;

import org.testng.annotations.*;

import CommonComponents.Common;
import excelUtil.ExcelUtil;
import genericActions.GenActions;
import stepBase.StepBase;

public class pollsTestCases extends StepBase
{
	GenActions action;
	ExcelUtil excel;
	
	public pollsTestCases()
	{
		super();
		action = new GenActions();
	}
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		action.lanchBrowser(BROWSERTYPE,URL);
	}
	@AfterMethod
	public void tearDown()

	{
		action.closeBrowser();
	}
	
	
	// Test Cases Block
	@Test
	public void Wavity_Login_Logout() throws InterruptedException, IOException
	{
		TestCaseName("WAV_001","Verfing_Login_Logout","Sanity");
		new Common().init();
		new Common().login();
		
	}
}

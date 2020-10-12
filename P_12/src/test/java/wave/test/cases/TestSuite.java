package wave.test.cases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.*;

import Generic.pack.GenericType;
import logs.pack.ExtentLogger;
import step.base.pack.StepBase;
import utilities.hub.pack.ExcelReadPack;
import xpath.hub.pack.XpathHub;
//@Listeners(Listener.pack.ListenersListen.class)
public class TestSuite extends StepBase
{
	public ExtentLogger logger;
	public ExcelReadPack read;
	public GenericType gen;
	public XpathHub xpaths;
	
	String testData[][],
	appCreationData[][],
	addControlsData[][],
	loginData[][],
	RecordsData[][];
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException
	{
		read = new ExcelReadPack();
		gen = new GenericType();
		xpaths = new XpathHub();
		xpaths.XpathHubStore();
		gen.LanchBrowser(read.GetDataAsPerHeader("ConfigFile.xlsx","Config",3,"Browser"),
				read.GetDataAsPerHeader("ConfigFile.xlsx","Config",4,"URL"));

	}
	
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result)
	{
		gen.CloseBrowser();
	}

	@Test(enabled = false,priority = 1,description = "Login And Logout")
	public void WAVE_TC_01() throws InterruptedException, IOException, AWTException
	{
		TestCaseName("WAVE-TC-01","Verifying Login Functionality");
		
		loginData = new ExcelReadPack().testDataReaderInCellRange("ConfigFile.xlsx","Config",3,3,5);
		keyword("Login Into The Application","Keyword","login",loginData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		appCreationData = new ExcelReadPack().testDataReaderInCellRange("TestData.xlsx","NewAppSheet",4,2,5);
		keyword("Creating Sample App To Test All The Properties","Keyword","createAnApp",appCreationData);
		
		addControlsData = new ExcelReadPack().testDataReaderInCellRange("TestData.xlsx","NewAppSheet",32,2,5);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",addControlsData);
		
		keyword("Logging Out From The Application","Keyword","logout");

	}
	
	@Test(enabled =false,priority = 2,description = "Creating An App")
	public void WAVE_TC_02() throws InterruptedException, IOException, AWTException
	{
		TestCaseName("WAVE-TC-02","Create An App With Text Control");
		
		testData = new ExcelReadPack().testDataReaderInCellRange("ConfigFile.xlsx","Config",4,3,5);
		keyword("Login Into The Application","Keyword","login",testData);
		keyword("Accepting The Coockie","Keyword","acceptCookie");
		
		/*
		testData = new ExcelReadPack().testDataReaderInCellRange("TestData.xlsx","appsData",3,2,4);
		keyword("Searching For Existing Apps and Delete If Any","Keyword","search_Delete_App",testData);
		
		testData=  new ExcelReadPack().testDataReaderInRow_CellRange("TestData.xlsx", "appsData",23,25,2,5);
		keyword("Adding Records If No Records Are In Old App","Keywoard","verifyExistingRecords",testData); */
		
		testData = new ExcelReadPack().testDataReaderInCellRange("TestData.xlsx","appsData",3,2,5);
		keyword("Creating An App","Keyword","createAnApp",testData);
		
		testData = new ExcelReadPack().testDataReaderInRow_CellRange("TestData.xlsx", "appsData",8,11,2,10);
		keyword("Adding Controls To An App","Keyword","addControls_setProprties",testData);
		
		testData=  new ExcelReadPack().testDataReaderInRow_CellRange("TestData.xlsx", "appsData",23,25,2,5);
		keyword("Creating The Records For Text Based Application","Keyword","addRecords",testData);
		keyword("Validating The Records Data In App Records List page","Keyword","dataValidationInLISTpage",testData);
		keyword("Validating The Records Data In App Records Inline Edit page","Keyword","dataValidationInLINE_EDITpage",testData);
		keyword("Validating The Records Data In App Records Normal Edit page","Keyword","dataValidationNORMALEDITpage",testData);
		
		
		keyword("Logging Out From The Application","Keyword","logout");
		
	}

}

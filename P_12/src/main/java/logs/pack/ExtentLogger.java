package logs.pack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentLogger 
{

	public ExtentHtmlReporter Report;
	public ExtentReports extent;
	public ExtentTest test,test1;
	
	public void extentInitailizer()
	{
		Report = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\TestReport.html");
		System.out.println("Initializing Extent Report at"+"V:\\FRAME\\EXP2.O\\Reports\\TestReport.html");
		
		Report.config().setDocumentTitle("Wavity Test results");
		Report.config().setReportName("WavityTestSuite");
		Report.config().setEncoding("UFT-8");
		Report.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(Report);
		extent.setSystemInfo("OS","Windows");
		extent.setSystemInfo("Browser","Chrome");
	}
	public void CreateTestCase(String testName,String Description)
	{
		System.out.println("Creating the Test with "+testName + " DESC "+Description);
		test = extent.createTest(testName,Description);
		 test.assignCategory("Groups"+"Smoke");
		 test.assignAuthor("Develpoer:"+"Vivek Reddy");
	}
	public void createKeyword(String Name)
	{
		test1=	test.createNode("Keyword:"+Name);
		System.out.println("Creating the Keyword with "+Name);
	}
	
	public void storeInReport()
	{
		extent.flush();
		System.out.print("Flusing The DATA");
	}
	
	public void infolog(String data)
	{
		test1.log(Status.INFO, data);
	}
	
	public void passLog(String data)
	{
		test1.log(Status.PASS, data);
	}
	public void errLog(String data)
	{
		test1.log(Status.FAIL, data );
	}

}

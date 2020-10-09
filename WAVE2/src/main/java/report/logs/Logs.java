package report.logs;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Logs 
{
	private static Logger log;
	
	private static ExtentHtmlReporter Report;
	private static ExtentReports extent;
	private static ExtentTest test,test1,test2,test3;

	public Logs()
	{
		DOMConfigurator.configure("log4j.xml");
		log = Logger.getLogger(Logs.class);
		
	}
	
	public void _INFO(String data)
	{
		log.info(data);
		//EXTENT_INFO(data);
		
	}
	
	public void _ERROR(String data)
	{
		log.error(data);
		//EXTENT_INFO(data);
	}
	

	public void extentInitailizer()
	{
		Report = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\TestReport.html");
		
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
		test = null;
		test = extent.createTest(testName,Description);
		 test.assignCategory("Group:"+" Smoke");
		 test.assignAuthor("AutomationEngineer:"+" Vivek Reddy");
		 test.assignDevice("OS:"+ " Windows");
	}
	public void createKeyword(String Name)
	{
		
		test1=	test.createNode("Keyword:"+Name);
	}
	
	public void storeInReport()
	{
		extent.flush();
		
	}
	
	public void EXTENT_INFO(String data)
	{
		
		test1.log(Status.INFO,data);
	}
	
	public void EXTENT_FAIL(String data)
	{
		test1.log(Status.FAIL,data);
	}

}

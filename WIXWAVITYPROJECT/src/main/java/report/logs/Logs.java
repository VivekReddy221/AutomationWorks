package report.logs;

import java.awt.Color;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import listeners.ListenEvents;

public class Logs 
{
	private static Logger log;
	
	static int i;
	static int loop,innerloop;
	static String StepData;
	
	
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
		test = extent.createTest(testName," ");
		 test.assignCategory("Group:"+" Smoke");
		 test.assignAuthor("AutomationEngineer:"+" Vivek Reddy");
		 test.assignDevice("OS:"+ " Windows");
		 test.info(MarkupHelper.createCodeBlock(testName+": "+ Description));
		 test1 = null;
		 i=0;
		 
	}
	public void createKeyword(String Name)
	{
		
		test1=	test.createNode(String.format("%s","<strong style='color:Lime;'>Keyword: "+Name+"</strong>"));
		i=1;
		loop = 0;
		innerloop = 0;
		test2 = test1;
	}
	
	public void storeInReport()
	{
		extent.flush();
		
	}
	public void EXTENT_INFO(String data)
	{
		StepData = null;
		StepData = data;
		
		if(i==0||i>1)
		{
		if(test1!=null) test1.log(Status.PASS,"Step: "+data);
		else
			{
			test1 = test;
			test1.log(Status.PASS,"Step: "+data);
			}
		}
		else
		{
			i++;
			
		//	test1.info(data);
		
			test1.info(MarkupHelper.createLabel(data, ExtentColor.PINK));
		}
	}
	
	public void EXTENT_FAIL(String data)
	{
		if(test1!=null) test1.log(Status.FAIL,String.format("%s","<br /><br />"+"Step: "+StepData+" Is Failed "+"<br/><br />"+data));
		else 
			{
			test1 = test;
			test.log(Status.FAIL,"Step: "+StepData+" "+data);
			}
	}
	
	public String LabelSetup(String Data)
	{
		StringBuffer buff = new StringBuffer();
		System.out.print(Data.length());
		int j = 132-Data.length();
		
		for(int x=0;x<j;x++)
		{
			if(x<(j/2)) buff.append(" ");
			else if(x==(j/2))
				buff.append(Data);
			else buff.append(" ");
		}

		String dataChanged = buff.toString();
		
		return dataChanged;
	}
	public void LoopIndex(String data,String Caption)
	{
		loop = loop +1;
		innerloop = 0;
		test1 = test2.createNode(String.format("%s","<strong style='color:Lime;'>Loop"+loop+": "+data+"</strong>"));
		test1.info(MarkupHelper.createLabel(Caption, ExtentColor.PINK));
		test3 = test1;
	}
	
	public void InnerLoopIndex(String data,String Caption)
	{
		innerloop++;
		test1 = test3.createNode(String.format("%s","<strong style='color:Lime;'>InnerLoop"+innerloop+": "+data+"</strong>"));
		test1.info(MarkupHelper.createLabel(Caption, ExtentColor.PINK));
	}
}

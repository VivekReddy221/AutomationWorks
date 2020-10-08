package Listener.pack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Generic.pack.GenericType;
import logs.pack.ExtentLogger;
import utilities.hub.pack.ExcelReadPack;


public class ListenersListen implements ITestListener 
{
	
	int failCount = 0, passCount = 0, skipCount = 0,PSNO=1,FSNO=1,pc=0,fc=0,sc=0,clear=0;
	FileInputStream FIS = null;
	FileOutputStream FOS= null;
	XSSFWorkbook book = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;
	XSSFCell cell2=null;
	
	FileOutputStream fos = null;
	
	String SNO,TestCaseId,TestDescription;
	
		@Override		
	    public void onStart(ITestContext test) 
	    {
			clear = clear + 1;
			
			try {
				FIS = new FileInputStream(System.getProperty("user.dir")+"\\Reports\\WavityTestReport.xlsx");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			try {
				book = new XSSFWorkbook(FIS);
			} catch (IOException e) {
			
				e.printStackTrace();
			} 	
		if(clear==1)
		{
			int count = book.getSheet("PassedTestCases").getLastRowNum();
			int count1 = book.getSheet("FailedTestCases").getLastRowNum();
			int count2 = book.getSheet("SkippedTestCases").getLastRowNum();
			
			System.out.println(" " + count +" "+count1+" "+count2);
			
			if(count>=1)
			{
				for(int i=1;i<=count;i++)
				{
					book.getSheet("PassedTestCases").removeRow(book.getSheet("PassedTestCases").getRow(i));
				}
			}
			else if(count1>=1)
			{
				for(int i=1;i<=count;i++)
				{
					book.getSheet("FailedTestCases").removeRow(book.getSheet("FailedTestCases").getRow(i));
				}
			}
			else if(count2>=1)
			{
				for(int i=1;i<=count;i++)
				{
					book.getSheet("SkippedTestCases").removeRow(book.getSheet("SkippedTestCases").getRow(i));
				}
			}
			
		}
	    }		
	
		@Override		
		public void onTestSuccess(ITestResult Result) 
		{	
			 	pc = pc + 1;
		       	
		       	SNO = Integer.toString(pc);
		       	TestCaseId = Result.getMethod().getMethodName();
		       	TestDescription = Result.getMethod().getDescription();
		       	
		       	System.out.println(" "+SNO+" "+TestCaseId+" "+TestDescription);
		       	
		   	String data[]= {SNO,TestCaseId,TestDescription,"Pass"};
		       	
		       	for(int i = 0 ;i<data.length;i++)
		       	{
		       		book.getSheet("PassedTestCases").getRow(pc).createCell(i).setCellValue(data[i]);
		       	}
		       	
				
				try {
					FIS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		       	try {
					fos = new FileOutputStream(System.getProperty("user.dir")+"\\Reports\\WavityTestReport.xlsx");
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				
				try {
					book.write(fos);
				} catch (IOException e) {
					
					e.printStackTrace();
				} 
				
		}	

    	@Override		
	    public void onTestFailure(ITestResult Result) 
	    {	
	    	fc = fc + 1;
	    	
	    	SNO = Integer.toString(fc);
	       	TestCaseId = Result.getMethod().getMethodName();
	       	TestDescription = Result.getMethod().getDescription();
	       	String failureReason = Result.getThrowable().toString();
	       	
	       	String data[]= {SNO,TestCaseId,TestDescription,"Fail",failureReason};
	       	
	       	for(int i = 0 ;i<data.length;i++)
	       	{
	       		book.getSheet("FailedTestCases").getRow(fc).createCell(i).setCellValue(data[i]);
	       	}		
	       	
			
			try {
				FIS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       	try {
				fos = new FileOutputStream(System.getProperty("user.dir")+"\\Reports\\WavityTestReport.xlsx");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			try {
				book.write(fos);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
	        		
	    }	
	    @Override		
	    public void onTestSkipped(ITestResult Result) 
	    {	
	    	sc = sc + 1;
	    	
	    	SNO = Integer.toString(sc);
	       	TestCaseId = Result.getMethod().getMethodName();
	       	TestDescription = Result.getMethod().getDescription();
	       	
	       	String data[]= {SNO,TestCaseId,TestDescription,"Skip"};
	       	
	       	for(int i = 0 ;i<data.length;i++)
	       	{
	       		book.getSheet("SkippedTestCases").getRow(sc).createCell(i).setCellValue(data[i]);
	       	}					
	        	
			
			try {
				FIS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	       	try {
				fos = new FileOutputStream(System.getProperty("user.dir")+"\\Reports\\WavityTestReport.xlsx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				book.write(fos);
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
	    }		
		@Override		
	    public void onFinish(ITestContext test) 
		{								
			
			
			try {
				book.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	    } 
}

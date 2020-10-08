package excelUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil 
{
	FileInputStream FIS = null;
	FileOutputStream FOS= null;
	XSSFWorkbook book = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null,cell2=null;
	String TestDataGetter[][];
	String TestDataGetter1[];
	public Hashtable<String,String> XpathCatcher;
	
	public void OpenExcel(String FileLocation,String SheetName) throws IOException
	{
		FIS = new FileInputStream(FileLocation);
		
		book = new XSSFWorkbook(FIS); 
		sheet = book.getSheet(SheetName);
	}
	
	public String[][] testDataReader(String FileLocation,String SheetName,int startRowNumber,int lastRowNumber) throws IOException
	{
		OpenExcel(FileLocation, SheetName);
		int last = sheet.getRow(0).getLastCellNum();
		
		if(startRowNumber==lastRowNumber) TestDataGetter = new String[1][last];
		else TestDataGetter = new String[lastRowNumber-startRowNumber][last];
		
		for(int i=startRowNumber-1,x=0;i<=lastRowNumber-1;i++,x++)
		{
			int lastCell =sheet.getRow(i).getLastCellNum();
			
			
			for(int j=0;j<lastCell;j++)
			{
				TestDataGetter[x][j]= sheet.getRow(i).getCell(j).getStringCellValue();
				
			}
		}
		return TestDataGetter;
		
	}
	
	public String[] testDataReaderAtParticluarCell(String FileLocation,String SheetName,int RowNumber,int CellNumber) throws IOException
	{
		OpenExcel(FileLocation, SheetName);
	    TestDataGetter1= new String[1];
	    TestDataGetter1[0]=sheet.getRow(RowNumber-1).getCell(CellNumber-1).getStringCellValue();
		return TestDataGetter1;
	}
	
	public void XpathReader() throws IOException
	{
		XpathCatcher = new Hashtable<String, String>();
		XpathCatcher.clear();
		OpenExcel("V:\\FRAME\\Project_V3\\WebUIMaps.xlsx","WebUIMaps");
		int LastRowNumber = sheet.getLastRowNum();
		
		for(int i=1,j=2,k=1;k<=LastRowNumber;k++)
		{
			row = sheet.getRow(k);
			cell = row.getCell(i);
			cell2 = row.getCell(j);

			XpathCatcher.put(cell.getStringCellValue(), cell2.getStringCellValue());
		}
		
	}
	
	public String xpathSender(String ElementName) throws IOException
	{
		XpathReader();
		return XpathCatcher.get(ElementName).toString();
	}
	
	public Properties ProReader(String PropLoc) throws IOException
	{
		FileReader reader=new FileReader(PropLoc); 
		  Properties p=new Properties();  
		    p.load(reader);  
		
		return p;
		
	}
}

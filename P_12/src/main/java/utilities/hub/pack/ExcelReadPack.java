package utilities.hub.pack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import logs.pack.Log;

public class ExcelReadPack extends Log
{

	FileInputStream FIS = null;
	FileOutputStream FOS= null;
	XSSFWorkbook book = null;
	protected XSSFSheet sheet = null;
	protected XSSFRow row = null;
	protected XSSFCell cell = null;
	protected XSSFCell cell2=null;
	String TestDataGetter[][];
	String TestDataGetter1[];
	String data = null;
	Hashtable<String,String> XpathCatcher;
	String returner;
	Object check[];
	
	public void OpenExcel(String FileLocation,String SheetName) throws IOException
	{
		FIS = new FileInputStream(System.getProperty("user.dir")+"\\"+FileLocation);
		
		book = new XSSFWorkbook(FIS); 
		sheet = book.getSheet(SheetName);
	}
	
	
	public String[][] testDataReader(String FileLocation,String SheetName,int headerno,int startRowNumber,int lastRowNumber) throws IOException
	{
		OpenExcel(FileLocation, SheetName);
		int last = sheet.getRow(headerno-1).getLastCellNum();
		
		System.out.println("Last Cell"+last);
		
		if(startRowNumber==lastRowNumber) TestDataGetter = new String[1][last];
		else TestDataGetter = new String[lastRowNumber-startRowNumber+1][last];
		
		for(int i=startRowNumber-1,x=0;i<=lastRowNumber-1;i++,x++)
		{
			int lastCell =sheet.getRow(i).getLastCellNum();
			
			System.out.print("RowLast Cell "+lastCell);
			for(int j=0;j<lastCell;j++)
			{
				if(sheet.getRow(i).getCell(j)!=null)
				TestDataGetter[x][j]= sheet.getRow(i).getCell(j).getStringCellValue();
				
			}
		}
		book.close();
		//log.info("Test data Retrived:[");
		for(int i=0;i<TestDataGetter.length;i++)
		{
			//log.info("DataSet"+(i+1)+"|-->");
			for(int j=0;j<TestDataGetter[i].length-1;j++)
			{
				//log.info(TestDataGetter[i][j]+" ");
			}
			//log.info("DataSet"+(i+1)+"-->|");
		}
		//log.info("Test data Retrived:]");
		return TestDataGetter;
		
	}
	public String[][] testDataReaderInRow_CellRange(String FileLocation,String SheetName,int startRowNumber,int lastRowNumber,int startcell, int endcell) throws IOException
	{
		OpenExcel(FileLocation, SheetName);
		
		if(startRowNumber==lastRowNumber) TestDataGetter = new String[1][endcell-startcell+1];
		
		else TestDataGetter = new String[lastRowNumber-startRowNumber+1][endcell-startcell+1];
		for(int i=startRowNumber-1,x=0;i<lastRowNumber;i++,x++)
		{
			for(int j = startcell-1,y=0;j<endcell;j++,y++)
			{
				if(sheet.getRow(i).getCell(j)!=null)
					
					//TestDataGetter[x][y] = setAnyTypeToString(sheet.getRow(i).getCell(j));
					
					TestDataGetter[x][y] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return TestDataGetter;
		
	}
	public String[][] testDataReaderInCellRange(String FileLocation,String SheetName,int RowNumber,int startcell,int lastCell) throws IOException
	{
		OpenExcel(FileLocation, SheetName);
		 TestDataGetter = new String[1][lastCell-startcell+1];
		 int rep = lastCell - startcell +1;
		 
			for(int j=startcell,x=0;x<rep;j++,x++)
			{
				if(sheet.getRow(RowNumber-1).getCell(j-1)!=null)
				{
					CellType celltype = sheet.getRow(RowNumber-1).getCell(j-1).getCellType();
					
					if(celltype==CellType.NUMERIC)
					{
							 String str = NumberToTextConverter.toText(sheet.getRow(RowNumber-1).getCell(j-1).getNumericCellValue());
							 
							 TestDataGetter[0][x]= str;
						
					}
	
					 if(celltype==CellType.STRING)
						{
							TestDataGetter[0][x]= sheet.getRow(RowNumber-1).getCell(j-1).getStringCellValue();
						}
					
				    }
			

					
					
					//TestDataGetter[0][x]= setAnyTypeToString(sheet.getRow(RowNumber-1).getCell(j-1));
					 
			// TestDataGetter[0][x]= sheet.getRow(RowNumber-1).getCell(j-1).getStringCellValue();
					
				}
				
			book.close();
		return TestDataGetter;
		
	}
	
	public String[] testDataReaderAtParticluarCell(String FileLocation,String SheetName,int RowNumber,int CellNumber) throws IOException
	{
		OpenExcel(FileLocation, SheetName);
	    TestDataGetter1= new String[1];
	    TestDataGetter1[0]=sheet.getRow(RowNumber-1).getCell(CellNumber-1).getStringCellValue();
	    book.close();
	    //log.info("Test Data is retrieved From File is"+TestDataGetter1[0]);
		return TestDataGetter1;
	}
	
	public void XpathReader() throws IOException
	{
		XpathCatcher = new Hashtable<String, String>();
		XpathCatcher.clear();
		OpenExcel("WebUIMaps.xlsx","WebUIMaps");
		int LastRowNumber = sheet.getLastRowNum();
		log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: READING WEB ELEMENT LOCATIONS INTO XPATH HUB:::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		for(int i=1,j=2,k=1;k<=LastRowNumber;k++)
		{
			
			row = sheet.getRow(k);
			cell = row.getCell(i);
			cell2 = row.getCell(j);
			if(row!=null && cell!=null && cell2!=null)
			{
				log.info("["+cell.getStringCellValue()+"]"+" = "+"["+cell2.getStringCellValue()+"]");
			XpathCatcher.put(cell.getStringCellValue(), cell2.getStringCellValue());
			}
			
		}
		book.close();
		
	}
	
	public String xpathSender(String ElementName) throws IOException
	{
		XpathReader();
		return XpathCatcher.get(ElementName).toString();
	}
	
	public String GetDataAsPerHeader(String FileLocation,String SheetName,int RowNumber,String HeaderName) throws IOException
	{
		OpenExcel(FileLocation,SheetName);
		int last = sheet.getRow(0).getLastCellNum();
		
		 TestDataGetter1= new String[1];
		 
		for(int i =0,j=0;i<last;i++)
		{
			if(sheet.getRow(j).getCell(i).getStringCellValue().contains(HeaderName))
			{
				TestDataGetter1[0]=  sheet.getRow(RowNumber-1).getCell(i).getStringCellValue().toString();
				
			}
		}
		book.close();
		return TestDataGetter1[0];
	}
	
	public void OpenExcelForWrite(String FileLocation) throws IOException
	{
		FIS = new FileInputStream(System.getProperty("user.dir")+"\\"+FileLocation);
		
		book = new XSSFWorkbook(FIS); 
	}
	
	public void OpenSheetToWrite(String SheetName,int rowNumber)
	{
		sheet = book.getSheet(SheetName);
	}
	
	public String setAnyTypeToString(XSSFCell Cell)
	{
		if (Cell.getCellType()==Cell.getCellType().STRING) 
		 {
			 returner =  cell.getStringCellValue();
			 
			 System.out.println("Stringggggg");
         }
		 else if (Cell.getCellType()==Cell.getCellType().NUMERIC) 
		 {
			 System.out.println("Integre..........");
			 double i = cell.getNumericCellValue();
			 
			 returner =  Integer.toString((int)i);
         } 
		 return returner;
	}
}

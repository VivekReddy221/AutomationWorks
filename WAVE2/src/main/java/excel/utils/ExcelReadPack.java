package excel.utils;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadPack 
{
	 public static FileInputStream FIS1;
	 public static FileOutputStream FOS;
	 public static XSSFWorkbook book[];
	 static int books;
	 
	 XSSFSheet sheet,sheet2;
	 XSSFRow row;
	 XSSFCell cell;
	 XSSFCell cell2=null;
		
	 String TestDataGetter[][];
	 String TestDataGetter1[];
	 String data = null;
	 Hashtable<String,String> XpathCatcher;
	 String returner;
	 Object check[];
		

	 public void OpenAWBooks(String workbooks[]) throws IOException
	{
		 books = workbooks.length; 
		 
		 book = new XSSFWorkbook[workbooks.length];
		 
		 for(int i=0;i<workbooks.length;i++)
		 {
			 FIS1 = new FileInputStream(System.getProperty("user.dir")+"\\"+workbooks[i]);
			 
			 book[i] = new XSSFWorkbook(FIS1);
		 }
	}
	 
	public void closeAllBooks() throws IOException

	{
		for(int i=0;i<books;i++)
		{
			book[i].close();
		}
	}
	

    public String[][] testDataReaderInCellRange(String SheetName,int RowNumber,int startcell,int lastCell) throws IOException
   { 
		   	sheet = sheetObjectReturn(SheetName);
		   	
			 TestDataGetter = new String[1][lastCell-startcell+1];
			 int rep = lastCell - startcell +1;
			 
				for(int j=startcell,x=0;x<rep;j++,x++)
				{
					if(sheet.getRow(RowNumber-1).getCell(j-1)!=null)
					{
							 
					 TestDataGetter[0][x]= readCellDataAsPerTheType(sheet.getRow(RowNumber-1).getCell(j-1));
						
					}
		        }
				
				return TestDataGetter;
	} 
	 
	public String readCellDataAsPerTheType(XSSFCell celltype)
	{
		String str=null;
		CellType type = celltype.getCellType();
		
		if(type==CellType.NUMERIC)
		{
				 str = NumberToTextConverter.toText(celltype.getNumericCellValue());
			
		}

		if(type==CellType.STRING)
		{
				str= celltype.getStringCellValue();
		}
		
		return str;
		
	}
	

	public XSSFSheet sheetObjectReturn(String SheetName)
	{	
		for(int i=0;i<books;i++)
		{
			if(book[i].getSheet(SheetName)!=null)
			{
				sheet2 = book[i].getSheet(SheetName);
			}
		}
				
		return sheet2;
		
	}
	   
	public String[][] testDataReaderInRow_CellRange(String SheetName,int startRowNumber,int lastRowNumber,int startcell, int endcell) throws IOException
	{
		sheet = sheetObjectReturn(SheetName);
		
		if(startRowNumber==lastRowNumber) TestDataGetter = new String[1][endcell-startcell+1];
		
		else TestDataGetter = new String[lastRowNumber-startRowNumber+1][endcell-startcell+1];
		
		for(int i=startRowNumber-1,x=0;i<lastRowNumber;i++,x++)
		{
			for(int j = startcell-1,y=0;j<endcell;j++,y++)
			{
				if(sheet.getRow(i).getCell(j)!=null)
				{
					TestDataGetter[x][y] = readCellDataAsPerTheType(sheet.getRow(i).getCell(j));
				}
			}
		}
		return TestDataGetter;
		
	} 
}

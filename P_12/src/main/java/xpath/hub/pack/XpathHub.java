
package xpath.hub.pack;

import java.io.IOException;
import java.util.Hashtable;

import utilities.hub.pack.ExcelReadPack;

public class XpathHub extends ExcelReadPack
{
	public static Hashtable<String,String> xpath_DB; // It will Store all X-paths
	
	// Default Constructor to read data from excel
	public  void XpathHubStore() throws IOException
	{
		xpath_DB = new Hashtable<String, String>();
		
		OpenExcel("WebUIMaps.xlsx","WebUIMaps");
		
		int LastRowNumber = sheet.getLastRowNum();
		log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::: READING WEB ELEMENT LOCATIONS INTO XPATH HUB:::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		log.info("----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i=1,j=2,k=1;k<=LastRowNumber;k++)
		{
			
			row = sheet.getRow(k);
			cell = row.getCell(i);
			cell2 = row.getCell(j);
			if(row!=null && cell!=null && cell2!=null)
			{
				log.info("["+cell.getStringCellValue()+"]"+" = "+"["+cell2.getStringCellValue()+"]");
		log.info("----------------------------------------------------------------------------------------------------------------------------------------------------------");
				xpath_DB.put(cell.getStringCellValue(), cell2.getStringCellValue());
			}
			
		}
	}
	
	// Using This Method, Required Xpath Will be Returned by matching element name
	public String xpathGetter(String ElementName) throws IOException
	{
		return xpath_DB.get(ElementName);
	}
	
	
}

package excel.utils;

import java.util.Date;

import report.logs.Logs;

public class StringClsUtil 
{
	String [] spitData;
	String replace;
	
	 static Logs log = new Logs();

	public String[] SplitData(String data)
	{
		log._INFO("Spiltting the Data: "+data);
		spitData = data.split(";");
		log._INFO("Data Spitted: [");
		log._INFO("DataSet |--->");
		for(int i=0;i<spitData.length;i++)
		{
			log._INFO(" "+spitData[i]);
		}
		log._INFO("DataSet --->|");
		log._INFO("Data Spitted: ]");
		return spitData;
	}
	
	public String ReplaceString(String Xpath,String replaceWith)
	{
		log._INFO("Expression : [$] is in String:"+Xpath+"is replacing with "+replaceWith);
		replace = Xpath.replace("[$]",replaceWith);
		log._INFO("After Conversion: "+replace);
		return replace;
	}
	public String ReplaceString1(String Xpath,String replaceWith)
	{
		log._INFO("Expression : [*] is in String:"+Xpath+"is replacing with "+replaceWith);
		replace = Xpath.replace("[*]",replaceWith);
		log._INFO("After Conversion: "+replace);
		return replace;
	}
	public boolean XpathConversion(String Xpath)
	{
		replace = Xpath.replace("[$]","xyz");
		return Xpath.equals(replace);
	}
	public String Convert_Customized_Format(Date Date)
	{
		;
		log._INFO("Symbols : or Space is in String:"+Date.toString()+"is replacing with "+"_");
		replace = Date.toString().replaceAll(":","_");
		String newReplace = replace.replaceAll(" ","_");
		log._INFO("After Conversion: "+new StringBuffer().append("_").append(newReplace));
		return new StringBuffer().append("_").append(newReplace).toString();
	}
}

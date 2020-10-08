package utilities.hub.pack;

import java.util.Date;

import logs.pack.Log;

public class StringClsUtil extends Log
{

	String [] spitData;
	String replace;
	
	public StringClsUtil()
	{
		super();
	}

	public String[] SplitData(String data)
	{
		log.info("Spiltting the Data: "+data);
		spitData = data.split(";");
		log.info("Data Spitted: [");
		log.info("DataSet |--->");
		for(int i=0;i<spitData.length;i++)
		{
			log.info(" "+spitData[i]);
		}
		log.info("DataSet --->|");
		log.info("Data Spitted: ]");
		return spitData;
	}
	
	public String ReplaceString(String Xpath,String replaceWith)
	{
		log.info("Expression : [$] is in String:"+Xpath+"is replacing with "+replaceWith);
		replace = Xpath.replace("[$]",replaceWith);
		log.info("After Conversion: "+replace);
		return replace;
	}
	public String ReplaceString1(String Xpath,String replaceWith)
	{
		log.info("Expression : [*] is in String:"+Xpath+"is replacing with "+replaceWith);
		replace = Xpath.replace("[*]",replaceWith);
		log.info("After Conversion: "+replace);
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
		log.info("Symbols : or Space is in String:"+Date.toString()+"is replacing with "+"_");
		replace = Date.toString().replaceAll(":","_");
		String newReplace = replace.replaceAll(" ","_");
		log.info("After Conversion: "+new StringBuffer().append("_").append(newReplace));
		return new StringBuffer().append("_").append(newReplace).toString();
	}
}

package logs.pack;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Log 
{
	public Logger log;
	public Log()
	{
		DOMConfigurator.configure("log4j.xml");
		log = Logger.getLogger(Log.class);
		
	}
}

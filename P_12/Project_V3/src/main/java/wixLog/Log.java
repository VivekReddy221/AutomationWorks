package wixLog;

import org.apache.log4j.*;
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

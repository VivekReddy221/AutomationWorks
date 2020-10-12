package TestCases;

import java.util.Arrays;

import org.apache.commons.mail.EmailException;

import send.mail.ReportAttacher;

public class Test 
{
	public static void main(String a[]) throws EmailException
	{
		
		new ReportAttacher().sendReport();
		
		
	}
}


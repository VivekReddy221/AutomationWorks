package TestCases;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.mail.EmailException;

import Generics.Generics;
import send.mail.ReportAttacher;

public class Test 
{
	public static void main(String a[]) throws EmailException, IOException
	{
		Generics gen = new Generics();
		gen.LanchBrowser("Chrome","http://tenant28.wavity.info");
		
	}
}


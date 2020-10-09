package specifics;

import java.awt.AWTException;
import java.io.IOException;

import Generics.Generics;
import StepsBase.StepBase;
import xpath.hub.XpathHub;



public class SpecificType extends StepBase
{
	public void login(String data[][]) throws InterruptedException, IOException, AWTException
	{
		/*Step("Verfiying The Login Card","verify","loginCard");
		Step("Verfiying The Wavity Logo","verify","logo");*/
		Step("Entering The Data In UserName","input","userName",data[0][0]);
		Step("Clicking The Next Button","click","next");
		/*
		Step("Verfiying The Login Card","verify","loginCard");
		Step("Verfiying The Wavity Logo","verify","logo");
		Step("Verifying The User Name In Password Entry Page","softAssert","validateUserName",data[0][0]);
		Step("Verifying The User Name In Password Entry Page","softAssert","validateUserName",data[0][2]);*/
		
		Step("Entering The Data In PassWord","input","passWord",data[0][1]);
		/*
		Step("Verfiying Forgot Password Link","verify","forgotPassword");
		Step("Verfiying Back Button","verify","back"); */
		Step("Clicking The Element Login","click","continue");
		
		// Step("Verifying The Signin Message","softAssert","validateLoginMessage","Successfully signed in as "+data[0][2]);
	}
	
	public void acceptCookie() throws IOException, InterruptedException, AWTException
	{
		new Generics().specificWait(new XpathHub().xpathGetter("acceptCookie"),2000);
		Step("Clicking On The Coockie","click","acceptCookie");
	}
	public void logout() throws InterruptedException, IOException, AWTException
	{
		
		Step("Clicking On User Account","click","userAccount");
		Step("Clickng On Logout","click","logout");
		
		new Generics().assertClose();

	}
}

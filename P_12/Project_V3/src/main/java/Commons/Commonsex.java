package Commons;

import java.io.IOException;

import genericActions.GenActions;
import stepBase.StepBase;
import wixLog.Log;

public class Commonsex extends Log
{
	
	public void login() throws InterruptedException, IOException
	{
			new StepBase().Step("userName","userName","swethak@wavity.com");
			new StepBase().Step("passWord", "passWord", "Wavity@6");
			new StepBase().Step("login","login");
	}
	
	public void logout() throws InterruptedException, IOException
	{
		new StepBase().Step("userAccount","userAccount");
		new StepBase().Step("logout","logout");
	}
	
	

	
}

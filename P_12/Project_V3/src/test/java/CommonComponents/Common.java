package CommonComponents;

import java.io.IOException;

import stepBase.StepBase;

public class Common
{
	StepBase ob;
	static String USERNAME="swethak@wavity.com",
				  PASSWORD="Wavity@6";
	
	public void init()
	{
		ob = new StepBase();
	}
	public void login() throws InterruptedException, IOException
	
	{
		ob.Step("Entering The Data In UserName","input","userName",USERNAME);
		ob.Step("Entering The Data In PassWord","input","passWord",PASSWORD);
		ob.Step("Clicking Login Button","click","login");
	}

}

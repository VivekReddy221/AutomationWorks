
package Specifics.pack;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Generic.pack.GenericType;
import step.base.pack.StepBase;
import utilities.hub.pack.StringClsUtil;
import xpath.hub.pack.XpathHub;

public class SpecificType extends StepBase
{
	String dataSplit[];
	String xpathConversion;
	
	public String DateSet()
	{
		long mills = System.currentTimeMillis();
		Date date = new Date(mills);
		
		return new StringClsUtil().Convert_Customized_Format(date);
	}
	
	public void search_Delete_App(String data[][]) throws InterruptedException, IOException, AWTException
	{
		Step("Clicking On Home Menu Button","click","homeMenu");
		
		Step("Clicking On Apps","click","apps");
		
		Step("Searching For An app is exist already or not","input","appSearch",data[0][0]);
		
		List<WebElement> elements = new GenericType().elementCount(new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("appsCount"),data[0][0]),"apps");
		
		int count = elements.size();
		

		for(int i = 0;i<count;i++)
		{
			
			String	xpath = new StringClsUtil().ReplaceString1(new XpathHub().xpathGetter("appSelect"),data[0][0]);
			
			xpathConversion = new StringClsUtil().ReplaceString(xpath,Integer.toString(1));
			
			Step("Clicking On Three Dot Menu App To Delete","click",xpathConversion,"YES","NO");
			
			if(i+1==count)
			{
				Step("Opening An Application","click","openApp");
			}
			else
			{
			Step("Clicking On Delete Option","click","deleteApp");
			Step("Accepting The App Deletion pop Up","click","deleteAppAccept");
			new GenericType().Sleep(8000);
			}
		}
		
	}
	public void verifyExistingRecords(String data[][]) throws IOException, InterruptedException, AWTException
	{
		List<WebElement> elements = new GenericType().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		if(size<2)
		{
			addRecords(data);
			dataValidationInLISTpage(data);
			dataValidationInLINE_EDITpage(data);
			dataValidationNORMALEDITpage(data);
		}
		else
		{		
			deleteAllRecords();
			new GenericType().Sleep(8000);
			addRecords(data);
			dataValidationInLISTpage(data);
			dataValidationInLINE_EDITpage(data);
			dataValidationNORMALEDITpage(data);
		}
	}
	public void login(String data[][]) throws InterruptedException, IOException, AWTException
	{
		Step("Verfiying The Login Card","verify","loginCard");
		Step("Verfiying The Wavity Logo","verify","logo");
		Step("Entering The Data In UserName","input","userName",data[0][0]);
		Step("Clicking The Next Button","click","next");
		Step("Verfiying The Login Card","verify","loginCard");
		Step("Verfiying The Wavity Logo","verify","logo");
		Step("Verifying The User Name In Password Entry Page","softAssert","validateUserName",data[0][0]);
		Step("Verifying The User Name In Password Entry Page","softAssert","validateUserName",data[0][2]);
		Step("Entering The Data In PassWord","input","passWord",data[0][1]);
		Step("Verfiying Forgot Password Link","verify","forgotPassword");
		Step("Verfiying Back Button","verify","back");
		Step("Clicking The Element Login","click","continue");
		// Step("Verifying The Signin Message","softAssert","validateLoginMessage","Successfully signed in as "+data[0][2]);
	}
	
	public void acceptCookie() throws IOException, InterruptedException, AWTException
	{
		new GenericType().specificWait(new XpathHub().xpathGetter("acceptCookie"),2000);
		Step("Clicking On The Coockie","click","acceptCookie");
	}
	public void logout() throws InterruptedException, IOException, AWTException
	{
		
		Step("Clicking On User Account","click","userAccount");
		Step("Clickng On Logout","click","logout");
		
		new GenericType().assertClose();

	}
	
	public void createAnApp(String data[][]) throws InterruptedException, IOException, AWTException
	{
		Step("Verifying W-Image On The HomePage","verify","wImage1");
		Step("Clicking on W-Image On The HomePage","click","wImage1");
		
		Step("Clicking On Apps","click","apps");
		
		Step("Clicking On Create New App Link","click","createNewApp");
		Step("Clicking On Create Button","click","createButton");
		Step("Verify App Name text","verify","appName");
		Step("Clicking On Choose Icon","click","chooseIcon");
		new GenericType().Sleep(4000);
		
		new GenericType().attachTheImage(data[0][0]);
		Step("Entering App Name","input","enterAppName",data[0][1]+DateSet());
		Step("Entering App Description","input","appDescription",data[0][2]);
		Step("Clicking on App Type","select","selectAppType",data[0][3]);
		Step("Clicking On Create Button","click","clickCreateApp");
	}
	
	public void addControls_setProprties(String data[][]) throws InterruptedException, IOException, AWTException
	{
		for(int i=0;i<data.length;i++)
		{
		Step("Clicking On MainControl On the App Designer","click","mainControl",data[i][0]);
	//	Step("Verifying Sub Control is Avalibale or not","verify","subControl",data[i][1]);
		Step("Dragging The Sub Control To App Controls Dropable Area","dragAndDrop","subControl","controlsDrop",data[i][1]);
		
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("controlLabel"),Integer.toString(i+1));
		Step("Entering App Control Label Name","input",xpathConversion,"YES",data[i][2]);
		
		//addProperties(data,i);
		
		}
		
		Step("Saving An Application","click","saveApp");
		new GenericType().Sleep(5000);
		Step("Verifying App Inactivation Message Available or Not","verify","notActivation");
		new GenericType().Sleep(5000);
		
		Step("Clicking On ThreeDot Menu To Activate The app","click","threeDotMenu");
		Step("Clicking on Active Button","click","activeApp");
		new GenericType().Sleep(2000);
		Step("Activating The App","click","activateApp");
		new GenericType().Sleep(5000);
	}
	public void addProperties(String data[][],int rowNumber) throws IOException, InterruptedException, AWTException
	{
		for(int i=rowNumber;i<=rowNumber;i++)
		{
			
			for(int j=3;j<data[i].length;j++)
			{
				if(data[i][j]!=null)
				{
					if(data[i][j].contains("required"))
					{
						dataSplit = new StringClsUtil().SplitData(data[i][j]);
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectProperty"),dataSplit[0]); 
						Step("Selecting The Required property","click",xpathConversion,"YES","NO");
					}
					else if(data[i][j].contains("hint"))
					{
						dataSplit = new StringClsUtil().SplitData(data[i][j]);
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectProperty"),dataSplit[0]); 
						Step("Inputing The Help Message property","input",xpathConversion,"YES",dataSplit[1]);
					}
									
										
				}
				
			}
		}
	}
	public void addRecords(String data[][]) throws InterruptedException, IOException, AWTException
	{
		for(int i=0;i<data.length;i++)
		{
			new GenericType().specificWait(new XpathHub().xpathGetter("addRecord"),30);
			Step("Clicking On New Button In App Records List page","click","addRecord");
			
			for(int j=0;j<data[i].length;j++)
			{
				if(data[i][j]!=null)
				{
					new GenericType().Sleep(2000);
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("addData"),Integer.toString(j+1));
					Step("Inputting The Data in control"+(j+1),"input",xpathConversion,"YES",data[i][j]);
				}
			}
			
			Step("Saving The Record","click","recordSave");
		}
	}
	
	public void dataValidationInLISTpage(String data[][]) throws IOException
	{
		List<WebElement> elements = new GenericType().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			
			List<WebElement> fields = new GenericType().elementCount(new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("verifyRecordInList"),Integer.toString(i+1))," Fields Found");
			int fsize = fields.size();
			log.info("================================================================================================================");
			log.info("Verifying The Data In The Record"+(i+1)+" In The Records List page");
			log.info("================================================================================================================");
			
			for(int j=1,y=0;j<fsize-1;j++,y++)
			{
				log.info("Verifying The Field"+j+"In The Record"+(i+1)+" In The Records List page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetListAtElement"),Integer.toString(i+1));
				String xpath = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(j+1));
				new GenericType().softAssert(xpath, data[x-1][y]);
				/*Boolean result = new GenericType().dataValidation(xpath, data[x-1][y]);
				assertTrue(result);*/
			}
		}
	}
	public void dataValidationInLINE_EDITpage(String data[][]) throws IOException, InterruptedException, AWTException
	{
		List<WebElement> elements = new GenericType().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			
			List<WebElement> fields = new GenericType().elementCount(new XpathHub().xpathGetter("inlinefieldsCount")," Fields Found");
			int fsize = fields.size();
			
			log.info("================================================================================================================");
			log.info("Verifying The Data In The Record"+(i+1)+" In The Records Inline Edit Page");
			log.info("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log.info("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetInlineAtElement"),Integer.toString(j+1));
				new GenericType().softAssert(xpathConversion, data[x-1][y]);
				/*boolean result = new GenericType().dataValidation(xpathConversion, data[x-1][y]);
				assertTrue(result); */
			}
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		}
		
	}
	public void dataValidationNORMALEDITpage(String data[][]) throws IOException, InterruptedException, AWTException
	{
        List<WebElement> elements = new GenericType().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			Step("Clciking On Edit In Inline Edit page","click","editFromInlinePage");
			
			List<WebElement> fields = new GenericType().elementCount(new XpathHub().xpathGetter("editPageElementCount")," Fields Found");
			int fsize = fields.size();
			
			log.info("================================================================================================================");
			log.info("Verifying The Data In The Record"+(i+1)+" In The Records Normal Edit Page");
			log.info("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log.info("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidateInRecordEditPage"),Integer.toString(j+1));
				new GenericType().softAssert(xpathConversion, data[x-1][y]);
				
				/*boolean result = new GenericType().dataValidation1(xpathConversion, data[x-1][y]);
				assertTrue(result);*/
			}
			
		Step("Clicking On Cancel Button In Normal Edit Page","click","cancelInEditPage");
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		
		}
	}
	
	public void InlineUpdate(String data[][]) throws IOException, InterruptedException, AWTException
	{
		List<WebElement> elements = new GenericType().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			
			List<WebElement> fields = new GenericType().elementCount(new XpathHub().xpathGetter("inlinefieldsCount")," Fields Found");
			int fsize = fields.size();
			
			log.info("================================================================================================================");
			log.info("Updating The Data In The Record"+(i+1)+" In The Records Inline Edit Page");
			log.info("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log.info("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetInlineAtElement"),Integer.toString(j+1));
				Step("Clicking On The Control To Enter The Data","click",xpathConversion,"YES","NO");
				Step("Entering The Data..","input",xpathConversion,"YES","Vivek");
				
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineSave"),Integer.toString(j+1));
				Step("Saving The Control In InLine-Edit","click",xpathConversion,"YES","NO");
				
				new GenericType().Sleep(4000);
			}
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		}
	}
	
	public void normalUpdate(String data[][])
	{
	}
	
	public void deleteAllRecords() throws InterruptedException, IOException, AWTException
	{
		Step("Selecting All The Records To Delete","click","selectAllRecordsToDelete");
		Step("Clicking On Delete","click","recDelete");
		Step("Accepting The Records Deletion Conformation Message","click","confmDelete");
	}
}


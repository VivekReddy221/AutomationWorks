package specifics;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

import Generics.Generics;
import StepsBase.StepBase;
import excel.utils.StringClsUtil;
import report.logs.Logs;
import xpath.hub.XpathHub;



public class SpecificType extends StepBase
{
	String dataSplit[];
	String xpathConversion;
	
    static Logs log = new Logs();
    
    static String testData[][],
	appCreationData[][],
	addControlsData[][],
	loginData[][],
	RecordsData[][];
    
    static String Subcontrol[],MainControl[],ControlLabels[];
	
	public String DateSet()
	{
		long mills = System.currentTimeMillis();
		Date date = new Date(mills);
		
		return new excel.utils.StringClsUtil().Convert_Customized_Format(date);
	}
	
	public void search_Delete_App(String data[][]) throws InterruptedException, IOException, AWTException
	{
		Step("Clicking On Home Menu Button","click","homeMenu");
		
		Step("Clicking On Apps","click","apps");
		
		Step("Searching For An app is exist already or not","input","appSearch",data[0][0]);
		
		List<WebElement> elements = new Generics().elementCount(new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("appsCount"),data[0][0]),"apps");
		
		int count = elements.size();
		

		for(int i = 0;i<count;i++)
		{
			log.LoopIndex("Deleting The Apps","Deleting The App"+(i+1));
			
			String	xpath = new excel.utils.StringClsUtil().ReplaceString1(new XpathHub().xpathGetter("appSelect"),data[0][0]);
			
			xpathConversion = new StringClsUtil().ReplaceString(xpath,Integer.toString(1));
			
			Step("Clicking On Three Dot Menu App To Delete","click","appSelect;"+xpathConversion,"YES","NO");
			
			/*if(i+1==count)
			{
				Step("Opening An Application","click","openApp");
			}
			else
			{*/
			Step("Clicking On Delete Option","click","deleteApp");
			Step("Accepting The App Deletion pop Up","click","deleteAppAccept");
			new Generics().Sleep(8000);
			//}
		}
		
	}
	public void verifyExistingRecords(String data[][]) throws IOException, InterruptedException, AWTException
	{
		/*List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
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
			new Generics().Sleep(8000);
			addRecords(data);
			dataValidationInLISTpage(data);
			dataValidationInLINE_EDITpage(data);
			dataValidationNORMALEDITpage(data); 
		}*/
	}
	public void login(String data[][]) throws InterruptedException, IOException, AWTException
	{
		Step("Verfiying The Login Card","verify","loginCard");
		Step("Verfiying The Wavity Logo","verify","logo");

		Step("Entering The Data In UserName","input","userName",data[0][0]);
		Step("Clicking The Next Button","click","next");
		
		Step("Verfiying The Login Card","verify","loginCard");
		Step("Verfiying The Wavity Logo","verify","logo");

		Step("Entering The Data In PassWord","input","passWord",data[0][1]);
		
		Step("Verfiying Forgot Password Link","verify","forgotPassword");
		Step("Verfiying Back Button","verify","back");
		
		Step("Clicking The Element Login","click","continue");
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
	
	public void createAnApp(String data[][]) throws InterruptedException, IOException, AWTException
	{
		appCreationData = data;
		
		Step("Verifying W-Image On The HomePage","verify","wImage1");
		Step("Clicking on W-Image On The HomePage","click","wImage1");
		
		Step("Clicking On Apps","click","apps");
		
		Step("Clicking On Create New App Link","click","createNewApp");
		Step("Clicking On Create Button","click","createButton");
		Step("Verify App Name text","verify","appName");
		//Step("Clicking On Choose Icon","click","chooseIcon");
		//new Generics().Sleep(4000);
		
		//new Generics().attachTheImage(data[0][0]);
		
		Step("Entering App Name","input","enterAppName",data[0][1]+DateSet());
		Step("Entering App Description","input","appDescription",data[0][2]);
		Step("Clicking on App Type","select","selectAppType",data[0][3]);
		Step("Clicking On Create Button","click","clickCreateApp");
	}
	
	public void addControls_setProprties(String data[][]) throws InterruptedException, IOException, AWTException
	{
		addControlsData = data;
		
		for(int i=0;i<data.length;i++)
		{
			log.LoopIndex("Adding The Main Control"+(i+1)+": "+data[i][0],"Adding Sub-Control"+": "+data[i][1]);
		Step("Clicking On MainControl On the App Designer","click","mainControl",data[i][0]);
		//new Generics().Sleep(1000);
	//	Step("Verifying Sub Control is Avalibale or not","verify","subControl",data[i][1]);
		Step("Dragging The Sub Control To App Controls Dropable Area","dragAndDrop","subControl","controlsDrop",data[i][1]);
		//new Generics().Sleep(1000);
		xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("controlLabel"),Integer.toString(i+1));
		Step("Entering App Control Label Name","input","controlLabel;"+xpathConversion,"YES",data[i][2]);
		//new Generics().Sleep(2000);
		
		addControlProperties(data[i][3]);
		
		}
		
		Step("Saving An Application","click","saveApp");
		new Generics().Sleep(5000);
	}
	public void addControlProperties(String ControlsProperties) throws IOException, InterruptedException, AWTException
	{
	
		String clickRelated="|Required|Deprecated";
		
		String propertySet[] = null;
		
		if(ControlsProperties!=null) 
		{
		propertySet=  new StringClsUtil().SplitData(ControlsProperties);
		
		for(int i =0;i<propertySet.length;i++)
		{
			log.InnerLoopIndex("Adding The Property: "+propertySet[i],"Property: "+propertySet[i]);
			
		if(propertySet[i]!=null)
		{
			
		if(clickRelated.contains(propertySet[i]))
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectProperty"),propertySet[i]); 
			Step("Selecting The "+propertySet[i] +"property","click","[selectProperty:"+propertySet[i]+"];"+xpathConversion,"YES","NO");
		}
		}
		}
		}
	}
	public void activateApp() throws InterruptedException, IOException, AWTException
	{
		Step("Verifying App Inactivation Message Available or Not","verify","notActivation");
		new Generics().Sleep(5000);
		
		Step("Clicking On ThreeDot Menu To Activate The app","click","threeDotMenu");
		Step("Clicking on Active Button","click","activeApp");
		new Generics().Sleep(2000);
		Step("Activating The App","click","activateApp");
		new Generics().Sleep(5000);
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
						Step("Selecting The Required property","click","[selectProperty]:Required;"+xpathConversion,"YES","NO");
					}
					else if(data[i][j].contains("hint"))
					{
						dataSplit = new StringClsUtil().SplitData(data[i][j]);
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("selectProperty"),dataSplit[0]); 
						Step("Inputing The Help Message property","input","[selectProperty]:HelpMessage;"+xpathConversion,"YES",dataSplit[1]);
					}
									
										
				}
				
			}
		}
	}
	public void addRecords(String data[][]) throws InterruptedException, IOException, AWTException
	{
		
		MainControl = new String[addControlsData.length];
		Subcontrol = new String[addControlsData.length];
		ControlLabels = new String[addControlsData.length];
		
		for(int i=0;i<addControlsData.length;i++)
		{
			MainControl[i] = addControlsData[i][0];
			Subcontrol[i]= addControlsData[i][1];
			ControlLabels[i]= addControlsData[i][2];
		}
		
		
		int endLooper = 0;
		
		for(int i=0;i<data[0].length;i++)
		{
			if(endLooper<data[0].length)	
			{
			log.LoopIndex("Adding The Records", "Data Insertion In The Record"+(i+1));
			new Generics().specificWait(new XpathHub().xpathGetter("addRecord"),30);
			Step("Clicking On New Button In App Records List page","click","addRecord");
			}
			
		
		for(int j=0;j<MainControl.length;j++)
		{
			
			if(data[j][i]!=null)
			{
		
			switch(MainControl[j])
			{
				case "Text": addDataToText(Subcontrol[j],ControlLabels[j],data[j][i]); break;
			}
			
			}
			
		}
		Step("Saving The Record","click","recordSave");
		endLooper = endLooper +1 ;
		}
	}
	
	public void dataValidationInLISTpage(String data[][]) throws IOException
	{
		List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			
			List<WebElement> fields = new Generics().elementCount(new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("verifyRecordInList"),Integer.toString(i+1))," Fields Found");
			int fsize = fields.size();
			log._INFO("================================================================================================================");
			log._INFO("Verifying The Data In The Record"+(i+1)+" In The Records List page");
			log._INFO("================================================================================================================");
			
			for(int j=1,y=0;j<fsize-1;j++,y++)
			{
				log._INFO("Verifying The Field"+j+"In The Record"+(i+1)+" In The Records List page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetListAtElement"),Integer.toString(i+1));
				String xpath = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(j+1));
				new Generics().softAssert(xpath, data[x-1][y]);
				/*Boolean result = new Generics().dataValidation(xpath, data[x-1][y]);
				assertTrue(result);*/
			}
		}
	}
	public void dataValidationInLINE_EDITpage(String data[][]) throws IOException, InterruptedException, AWTException
	{
		List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			
			List<WebElement> fields = new Generics().elementCount(new XpathHub().xpathGetter("inlinefieldsCount")," Fields Found");
			int fsize = fields.size();
			
			log._INFO("================================================================================================================");
			log._INFO("Verifying The Data In The Record"+(i+1)+" In The Records Inline Edit Page");
			log._INFO("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log._INFO("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetInlineAtElement"),Integer.toString(j+1));
				new Generics().softAssert(xpathConversion, data[x-1][y]);
				/*boolean result = new Generics().dataValidation(xpathConversion, data[x-1][y]);
				assertTrue(result); */
			}
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		}
		
	}
	public void dataValidationNORMALEDITpage(String data[][]) throws IOException, InterruptedException, AWTException
	{
        List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			Step("Clciking On Edit In Inline Edit page","click","editFromInlinePage");
			
			List<WebElement> fields = new Generics().elementCount(new XpathHub().xpathGetter("editPageElementCount")," Fields Found");
			int fsize = fields.size();
			
			log._INFO("================================================================================================================");
			log._INFO("Verifying The Data In The Record"+(i+1)+" In The Records Normal Edit Page");
			log._INFO("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log._INFO("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidateInRecordEditPage"),Integer.toString(j+1));
				new Generics().softAssert(xpathConversion, data[x-1][y]);
				
				/*boolean result = new Generics().dataValidation1(xpathConversion, data[x-1][y]);
				assertTrue(result);*/
			}
			
		Step("Clicking On Cancel Button In Normal Edit Page","click","cancelInEditPage");
		Step("Clicking On Back Button In Inline Edit page","click","backFromInlineEditToListPage");
		
		}
	}
	
	public void InlineUpdate(String data[][]) throws IOException, InterruptedException, AWTException
	{
		List<WebElement> elements = new Generics().elementCount(new XpathHub().xpathGetter("countRecordsInList")," Records Found");
		
		int size = elements.size();
		
		for(int i=0,x=size;i<size;i++,x--)
		{
			xpathConversion = new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineEditLink"),Integer.toString(i+1));
			Step("Clicking On App Record Inline Edit link For Record"+(i+1),"click",xpathConversion,"YES","NO");
			
			List<WebElement> fields = new Generics().elementCount(new XpathHub().xpathGetter("inlinefieldsCount")," Fields Found");
			int fsize = fields.size();
			
			log._INFO("================================================================================================================");
			log._INFO("Updating The Data In The Record"+(i+1)+" In The Records Inline Edit Page");
			log._INFO("================================================================================================================");
			
			for(int j=0,y=0;j<fsize;j++,y++)
			{
				log._INFO("Verifying The Field"+(j+1)+"In The Record"+(i+1)+" In The Records Inline Edit page");
				xpathConversion = new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("dataValidatetInlineAtElement"),Integer.toString(j+1));
				Step("Clicking On The Control To Enter The Data","click",xpathConversion,"YES","NO");
				Step("Entering The Data..","input",xpathConversion,"YES","Vivek");
				
				xpathConversion = new excel.utils.StringClsUtil().ReplaceString(new XpathHub().xpathGetter("inlineSave"),Integer.toString(j+1));
				Step("Saving The Control In InLine-Edit","click",xpathConversion,"YES","NO");
				
				new Generics().Sleep(4000);
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
	
	public void addDataToText(String subControl,String ControlLabel,String data) throws InterruptedException, IOException, AWTException
	{
		 switch(subControl)
			{
				case "Plain": 
				{
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToTextControl_Plain_2F_3F_Block"),ControlLabel);
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(1));
					Step("Inputting The Data in "+subControl+" control","input","AddDataToTextControl_Plain_2F_3F_Block;"+xpathConversion,"YES",data);
				}
				break;
				case "Two Fields": 
				{
					String[] twoF = new StringClsUtil().SplitData(data);
					
					for(int x=0;x<twoF.length;x++)
					{
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToTextControl_Plain_2F_3F_Block"),ControlLabel);
						xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(x+1));
						Step("Inputting The Data in "+subControl+" control","input","AddDataToTextControl_Plain_2F_3F_Block;"+xpathConversion,"YES",twoF[x]);
					
					}
				}
				break;
				case "Three Fields": 
				{
					String[] threeF = new StringClsUtil().SplitData(data);
					
					for(int x=0;x<threeF.length;x++)
					{
						xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToTextControl_Plain_2F_3F_Block"),ControlLabel);
						xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(x+1));
						Step("Inputting The Data in "+subControl+" control","input","AddDataToTextControl_Plain_2F_3F_Block;"+xpathConversion,"YES",threeF[x]);
					}
				}
				break;
				case "Text Block": 
				{
					xpathConversion = new StringClsUtil().ReplaceString(new XpathHub().xpathGetter("AddDataToTextControl_Plain_2F_3F_Block"),ControlLabel);
					xpathConversion = new StringClsUtil().ReplaceString1(xpathConversion,Integer.toString(1));
					Step("Clicking On The Text Block","click",xpathConversion,"YES","NO");
					Step("Inputting The Data in "+subControl+" control","input","AddDataToTextControl_Plain_2F_3F_Block;"+xpathConversion,"YES",data);
				}
				break;
			}
	}
}

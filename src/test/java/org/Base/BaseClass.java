package org.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class BaseClass {
	public static WebDriver driver;
	
	public static WebDriver chromBrowser(){
		driver=new ChromeDriver();
		return driver;
	}
	public static WebDriver browserLaunch(String bname) {
		if(bname.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
	    }
		if(bname.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
	    }
		if(bname.equalsIgnoreCase("fireFox")) {
			driver=new FirefoxDriver();
	    }
		return driver;
	}
	public static void urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	public static void implicityWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public static void sendKeys(WebElement e,String value) {
		e.sendKeys(value);
	}
	public static void click(WebElement e) {
		e.click();
	}
	
	public static String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public static void quit() {
		driver.quit();
	}
	public static String getAttribute(WebElement e) {                    //Attribute
		String attribute = e.getAttribute("value");
		return attribute;
	}
	public static void moveToElement(WebElement e) {
		Actions a=new Actions(driver);
		a.moveToElement(e).perform();
	}
	public static void dragAndDrop(WebElement src,WebElement dest) {
		Actions a=new Actions(driver);
		a.dragAndDrop(src, dest).perform();
	} 
	public static void selectByIndex(WebElement e,int a) {
		Select s=new Select(e);
		s.selectByIndex(a);
	}
	public static void getWindowHandles(WebElement e,int a) {             //Window handles
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> li=new ArrayList<String>();
		li.addAll(windowHandles);
		driver.switchTo().window(li.get(a));
	}
	public static String  getWindowHandle() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;		
	}
	public  static String getFirstSelectedOptions(WebElement e) {
		Select s=new Select(e);
		WebElement firstSelectedOption = s.getFirstSelectedOption();
		String text =firstSelectedOption.getText();
		return text;		
	}
	public static void getScreeshot() throws IOException {                                //TakesScreenshot
	long yu = System.currentTimeMillis();
	TakesScreenshot take=(TakesScreenshot)driver;
	File sourceFile =take.getScreenshotAs(OutputType.FILE);
	File destinationFile=new File("C:\\Users\\Srikanth\\eclipse-workspace\\NewMaven\\screenshot\\"+yu+".png");
	FileUtils.copyFile(sourceFile, destinationFile);
	}
	public static void dropDown(WebElement e,int a) {
		Select s=new Select(e);
		s.selectByIndex(a);
	}
	public static void setAttribute(WebElement e,String val) {
		JavascriptExecutor j=(JavascriptExecutor)driver;
		j.executeScript("arguments[0].setAttribute('value','val')", e);
	}
	
	public static String exclevalue(String filename,String sheet,int row ,int c ) throws IOException {	
	File w=new File("C:\\Users\\Srikanth\\eclipse-workspace\\NewMaven\\Excel\\"+filename+".xlsx");
	
	FileInputStream st=new FileInputStream(w);
	
	Workbook wo=new XSSFWorkbook(st);
	Sheet s = wo.getSheet(sheet);
	Row r = s.getRow(row);
	Cell cell = r.getCell(c);
	int cellType = cell.getCellType();
	String value=null;
	if(cellType==1) {
		 value = cell.getStringCellValue();
	}
	else {
		if(DateUtil.isCellDateFormatted(cell)) {
			Date dateCellValue = cell.getDateCellValue();
			SimpleDateFormat sd= new SimpleDateFormat();
			 value = sd.format(dateCellValue);
		}
		else {
			double numericCellValue = cell.getNumericCellValue();
			long num=(long)numericCellValue;
			value = String.valueOf(num);
		}
	}
	return value;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	


package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.pom.LoginPage;

public class Baseclass 
{
	FileLibrary fl=new FileLibrary();
	public WebDriver driver;
	@BeforeSuite
	public void dataBaseConnection()
	{
		Reporter.log("detabase connected",true);
	}
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String actitimeurl = fl.readDataFromPropertyFile("url");
		driver.get(actitimeurl);
		Reporter.log("browser is launched",true);
	}
	@BeforeMethod
	public void login() throws IOException
	{
		LoginPage lp=new LoginPage(driver);
		String un = fl.readDataFromPropertyFile("username");
		String pwd = fl.readDataFromPropertyFile("password");
		lp.getUntbx().sendKeys(un);
		lp.getPwtbx().sendKeys(pwd);
		lp.getLgbtn().click();
		Reporter.log("logged in to actitime",true);
	}
	@AfterMethod
	public void logout()
	{
		driver.findElement(By.id("logoutLink")).click();
		Reporter.log("logged out Successfully",true);
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
		Reporter.log("browser is closed",true);
	}
	@AfterSuite
	public void dataBasedisConnection()
	{
		Reporter.log("detabase disconnected",true);
	}

}

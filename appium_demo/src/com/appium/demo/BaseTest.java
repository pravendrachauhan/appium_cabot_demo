package com.appium.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	private static AppiumDriverLocalService service;
	static AppiumDriver<WebElement> driver;
	
	
	@BeforeSuite
	public void beforeSuite(){
		startAppium();
	}
	
	@BeforeClass
	@Parameters("type")
	public void beforeClass(String type) throws MalformedURLException{
		startDriver(type);
	}
	
	@AfterClass
	public void afterClass(){
		closeDriver();
	}
	
	@AfterSuite
	public void afterSuite(){
		stopAppium();
	}
	

	public void startAppium(){	

		String osName = System.getProperty("os.name").toLowerCase();

		String nodePath = null;
		String appiumPath = null;

		if (osName.contains("mac")) {
			//            mac path
			nodePath = "/usr/local/bin/node";
			appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
		} else if (osName.contains("linux")) {
			//      linux path
			nodePath = System.getenv("HOME") + "/.linuxbrew/bin/node";
			appiumPath = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
		}
		else if(osName.contains("windows")){
			//          windows path
			nodePath = "C:\\Program Files (x86)\\Appium\\node.exe";
			appiumPath = "C:\\Program Files (x86)\\Appium\\node_modules\\appium\\lib\\server\\main.js";
		}
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		.usingDriverExecutable(new File(nodePath))
		.usingPort(4723)
		.withAppiumJS(new File(appiumPath)));

		service.start();	
	}
	
	public void stopAppium(){	
		service.stop();
	}
	
	public void startDriver(String type) throws MalformedURLException{
		DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.101.101:5555");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
		desiredCapabilities.setCapability("deviceName", "Android Emulator");
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");
		desiredCapabilities.setCapability("locale", "US");
		desiredCapabilities.setCapability("deviceReadyTimeout", "450");
	
		if(type.equalsIgnoreCase("WEB_APP")){
			desiredCapabilities.setCapability("browserName", "Browser");
		}
		else if(type.equalsIgnoreCase("HYBRID_APP")){
			String packageName="com.testdroid.sample.android";
			String apkName="TestDroid.apk";
			File classpathRoot=new File(System.getProperty("user.dir"));
			File appDir=new File(classpathRoot,"//testdata//apk");
			File app= new File(appDir, apkName);
			desiredCapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			desiredCapabilities.setCapability("appPackage", packageName);
			desiredCapabilities.setCapability("appActivity", packageName+".MM_MainMenu");
		}
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		
	}
	public void closeDriver(){
		driver.quit();
	}

}

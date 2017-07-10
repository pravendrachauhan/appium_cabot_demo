package com.appium.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;







import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class HybridAppAppium extends BaseTest{
	private static AppiumDriverLocalService service;

//	public static void main(String[] args) throws MalformedURLException {
		@Test
		public void testFunc() throws MalformedURLException, InterruptedException {	
					
		String packageName="com.testdroid.sample.android";
				
		driver.findElement(By.id(packageName+":id/mm_b_hybrid")).click();
		
		Thread.sleep(15000);
		
		for(String temp: driver.getContextHandles()){
			System.out.println(temp);
		}
		
		System.out.println("current conext: "+driver.getContext());
		
		driver.context("WEBVIEW_"+packageName);
		
		System.out.println("current conext: "+driver.getContext());

		driver.findElement(By.id("lst-ib")).sendKeys("cabot");

//trying to create merging situation		
		
//		driver.findElement(By.xpath("//android.widget.Spinner")).sendKeys("cabot");
//		
//		driver.findElement(By.xpath("//android.widget.Button[@content-desc='Google Search']")).click();

		
	}

}

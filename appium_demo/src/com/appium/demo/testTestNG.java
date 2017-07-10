package com.appium.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testTestNG {
	
	@Test(priority=1)
	public void login(){
		System.out.println("test1");
	}
	@Test(priority=2, dependsOnMethods={"login"})
	public void arvind(){
		System.out.println("test2");
	}
	
	@Test(priority=3, dependsOnMethods={"login"},enabled=false)
	public void basic(){
		System.out.println("test3");
	}

}

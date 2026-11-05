package com.generic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	    protected static final String BASE_URI = FileLib.get("BASE_URI");
	    protected RequestSpecification spec;
	    
	    @BeforeSuite
	    public void beforeSuite() {
	    	 System.out.println("@BeforeSuite: suite start");
	    }
	    @BeforeClass
	    public void setup() {
	    	String user = FileLib.get("LEBYY_USER");
	        String pass = FileLib.get("LEBYY_PASS");
	        
	        spec = new RequestSpecBuilder()
	                .setBaseUri(BASE_URI)
	                .setContentType(ContentType.JSON)
	                .setAuth(RestAssured.basic(user, pass))
	                .build();
	    }
	    @BeforeMethod
	    public void beforeMethod() {
	        System.out.println("@BeforeMethod: test start");
	    }
	    @AfterMethod
	    public void afterMethod() {
	        System.out.println("@AfterMethod: test end");
	    }
	    @AfterClass
	    public void tearDown() {
	        spec = null;
	    }
	    @AfterSuite
	    public void afterSuite() {
	        System.out.println("@AfterSuite: suite end");
	    }

	    
}

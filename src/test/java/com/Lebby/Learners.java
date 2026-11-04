package com.Lebby;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class Learners {
	@Test(priority = 1)
	public void testGetAllLearners() {
		
		System.out.println("*********** Welcome In Testing World**********");
		System.out.println("*********** Welcome In Automation Testing World**********");
		
		System.out.println("*********** Started:Test Case: Get All Learners **********");
		
		given()
			.auth().basic("api_learner", "Lebyy@Api1")
			
		.when()
			.get("https://lebyy.com/practice/api/learners")
		.then() 
			.log().body()
			.statusCode(200);
		System.out.println("*********** Ended:Test Case: Get All Learners **********");	
	}
	@Test(priority = 2)
	public void testGetLearnersById() {
		
		System.out.println("*********** Started:Test Case: Get Learners By ID **********");
		
		given()
			.auth().basic("api_learner", "Lebyy@Api1")
			.pathParam("id", 3)
			
		.when()
			.get("https://lebyy.com/practice/api/learners/{id}")
		.then() 
			.log().body()
			.statusCode(200)
			.log().all();
		System.out.println("*********** Ended:Test Case: Get Learners By ID **********");
		
	}
}

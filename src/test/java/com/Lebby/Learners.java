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
}

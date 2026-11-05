package com.Lebby;

import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.generic.FileLib;

import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.io.File;
public class Learners extends BaseTest{
	
	
	private static final String LEARNERS = "/practice/api/learners";
	private int createdId;
	

	
	@Test(priority = 1)
	public void GetAllLearners() {
		
		given().spec(spec).get(LEARNERS).then().statusCode(200).log().body();	
	}
	@Test(priority =2)
	public void CreateLearner() {
		
		 String DATA_DIR = FileLib.get("DATA_DIR");
		    File file = new File(DATA_DIR + "lebyy.json");
		    
		 createdId = given().spec(spec)
                    .body(new File(DATA_DIR+"lebyy.json"))
		.when()
		       .post(LEARNERS)
		.then().statusCode(201)
		       .body("id", notNullValue())
		       .extract().path("id");
		       
	}

	@Test(priority = 3,dependsOnMethods="CreateLearner")
	public void getLearnersById() {
		
		given().spec(spec)
			
		.when()
			.get(LEARNERS+"/"+createdId)
		.then() 
		
			.statusCode(200)
			.body("id", equalTo(createdId))
			.log().all();
		
		
	}
	
	@Test(priority =4 ,dependsOnMethods = "CreateLearner")
	public void updateLearner() {
		 String DATA_DIR = FileLib.get("DATA_DIR");
		    File file = new File(DATA_DIR + "updateLebyy.json");
		
		given().spec(spec)
		       .body(new File(DATA_DIR+"updateLebyy.json"))
		.when()
		      .put(LEARNERS+"/"+createdId)
		.then()
		      .statusCode(200);
		      
	}
	@Test(priority = 5, dependsOnMethods = "CreateLearner")
	public void partialUpdateLerner() {
		 String DATA_DIR = FileLib.get("DATA_DIR");
		    File file = new File(DATA_DIR + "partialUpdateLebyy.json");
		
		given().spec(spec)
		     
		     .body(new File(DATA_DIR+"partialUpdateLebyy.json"))
		.when()
		      .patch(LEARNERS+"/"+createdId)
		.then()
		      .statusCode(200);
	}
	@Test(priority= 6 ,dependsOnMethods = "CreateLearner")
	public void deleteLearner() {
		given().spec(spec)
		      
		.when()
		      .delete(LEARNERS+"/"+createdId)
		.then()
		       .statusCode(200);
		given().spec(spec)
		.when().get(LEARNERS+"/"+createdId)
		.then().statusCode(404);
	}
}

package MyApiPackage;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(MyApiPackage.MyTestListener.class)

public class Class2 {
	
	@Test(priority=1)
    public void testUiAndApi() {
		
		SoftAssert SoftAssert = new SoftAssert();
     
    	// Define the base URI for RestAssured
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
     // Send GET request to the endpoint
        Response response = RestAssured.given()
            .when()
            .get("/pet/123");

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        
     // Validate the response body
        SoftAssert.assertEquals(response.jsonPath().getInt("id"), 123, "ID does not match");
        SoftAssert.assertEquals(response.jsonPath().getString("name"), "flaunder", "Name does not match");
        SoftAssert.assertEquals(response.jsonPath().getString("status"), "available", "Status does not match");

        // Validate category
        SoftAssert.assertEquals(response.jsonPath().getInt("category.id"), 312, "Category ID does not match");
        SoftAssert.assertEquals(response.jsonPath().getString("category.name"), "waterpet", "Category name does not match");

        // Validate tags
        //Assert.assertEquals(response.jsonPath().getString("tags[0].name"), "Cute", "First tag name does not match");
        //Assert.assertEquals(response.jsonPath().getString("tags[1].name"), "Adorable", "Second tag name does not match");

        // Print response body for debugging
        System.out.println("Response Body: " + response.getBody().asString());
        
     // Assert all
        SoftAssert.assertAll();
    }
}

package MyApiPackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

@Listeners(MyApiPackage.MyTestListener.class)
public class MyApiClass {

    private static final Logger logger = LogManager.getLogger(MyApiClass.class);

    @Test(priority=1)
    public void testUiAndApi() throws IOException {

        // Initialize Excel utility
        ExelReader excelUtils = new ExelReader("./testdata.xlsx");

        
        // Get data from Excel with error handling for decimal values
        String petIdStr = excelUtils.getCellData("Sheet1", 1, 0);
        int petId = parseIntSafe(petIdStr);
        
        String expectedName = excelUtils.getCellData("Sheet2", 1, 0);
        String expectedStatus = excelUtils.getCellData("Sheet2", 1, 1);
        String expectedCategoryIdStr = excelUtils.getCellData("Sheet2", 1, 2);
        int expectedCategoryId = parseIntSafe(expectedCategoryIdStr);
        String expectedCategoryName = excelUtils.getCellData("Sheet2", 1, 3);
        // Define the base URI for RestAssured
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Send GET request to the endpoint
        Response response = RestAssured.given()
            .when()
            .get("/pet/"  +petId );

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200.");

        // Print response body for debugging
        logger.info("Response Body: " + response.getBody().asString());

        
        // Validate the response body
        Assert.assertEquals(response.jsonPath().getInt("id"), petId, "ID does not match");
        Assert.assertEquals(response.jsonPath().getString("name"), expectedName, "Name does not match");
        Assert.assertEquals(response.jsonPath().getString("status"), expectedStatus, "Status does not match");

        // Validate category
        Assert.assertEquals(response.jsonPath().getInt("category.id"), expectedCategoryId, "Category ID does not match");
        Assert.assertEquals(response.jsonPath().getString("category.name"), expectedCategoryName, "Category name does not match");


        // Close Excel utility
        excelUtils.close();
    }
 // Safe parsing method to handle NumberFormatException
    private int parseIntSafe(String value) {
        try {
            return (int) Double.parseDouble(value);
        } catch (NumberFormatException e) {
        	logger.error("Failed to parse integer from value: " + value, e);
            return 0; // Or handle it in a way that makes sense for your use case
        }
    
}
}

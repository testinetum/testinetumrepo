package MyPackage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class StepDefinitions {

    private Response response;
    private SoftAssert softAssert = new SoftAssert();

    @Given("I set the base URI to {string}")
    public void i_set_the_base_uri_to(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = RestAssured.given().when().get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode, "Status code is not " + statusCode);
    }

    @Then("the pet ID should be {int}")
    public void the_pet_id_should_be(int id) {
        softAssert.assertEquals(response.jsonPath().getInt("id"), id, "ID does not match");
    }

    
    @Then("the pet name should be {string}")
    public void the_pet_name_should_be(String name) {
        softAssert.assertEquals(response.jsonPath().getString("name"), name, "Name does not match");
    }

    @Then("the pet status should be {string}")
    public void the_pet_status_should_be(String status) {
        softAssert.assertEquals(response.jsonPath().getString("status"), status, "Status does not match");
    }

    @Then("the category ID should be {int}")
    public void the_category_id_should_be(int categoryId) {
        softAssert.assertEquals(response.jsonPath().getInt("category.id"), categoryId, "Category ID does not match");
    }

    @Then("the category name should be {string}")
    public void the_category_name_should_be(String categoryName) {
        softAssert.assertEquals(response.jsonPath().getString("category.name"), categoryName, "Category name does not match");
    }
 
 	
     @Then("the first tag name should be {string}")
     public void the_first_tag_name_should_be(String tagName) {
         softAssert.assertEquals(response.jsonPath().getString("tags[0].name"), tagName, "First tag name does not match");
     }
    
  
    
    @Then("I print the response body")
    public void i_print_the_response_body() {
        System.out.println("Response Body: " + response.getBody().asString());
        softAssert.assertAll();
    }
}

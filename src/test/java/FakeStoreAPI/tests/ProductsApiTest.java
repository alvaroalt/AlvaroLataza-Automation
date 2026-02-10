package FakeStoreAPI.tests;

import FakeStoreAPI.base.ApiBaseTest;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProductsApiTest extends ApiBaseTest {

    @Test(description = "GET /products returns 200 and non-empty list")
    public void getAllProducts() {
        Response response = given()
            .when()
                .get("/products");

        Assert.assertEquals(response.getStatusCode(), 200);
        List<WebElement> products = response.jsonPath().getList("$");
        Assert.assertFalse(products.isEmpty(), "Products list should not be empty");

        response.then().body(matchesJsonSchemaInClasspath("schemas/products-schema.json"));

        System.out.println("PASSED: getAllProducts - returned " + products.size() + " products (schema valid)");
    }

    @Test(description = "GET /products/1 returns 200 with correct id")
    public void getProductById() {
        Response response = given()
            .when()
                .get("/products/1");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("title"), "Title should not be null");
        Assert.assertNotNull(response.jsonPath().get("price"), "Price should not be null");
        System.out.println("PASSED: getProductById - product title: " + response.jsonPath().getString("title"));
    }

    @Test(description = "GET /products/category/electronics returns 200")
    public void getProductsByCategory() {
        Response response = given()
            .when()
                .get("/products/category/electronics");

        Assert.assertEquals(response.getStatusCode(), 200);
        List<WebElement> products = response.jsonPath().getList("$");
        Assert.assertFalse(products.isEmpty(), "Electronics list should not be empty");

        List<String> categories = response.jsonPath().getList("category");
        for (String category : categories) {
            Assert.assertEquals(category, "electronics", "All products should be electronics");
        }
        System.out.println("PASSED: getProductsByCategory - returned " + products.size() + " electronics products");
    }
}

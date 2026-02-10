package FakeStoreAPI.tests;

import FakeStoreAPI.base.ApiBaseTest;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CartApiTest extends ApiBaseTest {

    @Test(description = "GET /carts returns 200 and non-empty list")
    public void getAllCarts() {
        Response response = given()
            .when()
                .get("/carts");

        Assert.assertEquals(response.getStatusCode(), 200);
        List<WebElement> carts = response.jsonPath().getList("$");
        Assert.assertFalse(carts.isEmpty(), "Carts list should not be empty");
        System.out.println("PASSED: getAllCarts - returned " + carts.size() + " carts");
    }

    @Test(description = "GET /carts/1 returns 200 with correct id")
    public void getCartById() {
        Response response = given()
            .when()
                .get("/carts/1");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().get("userId"), "userId should not be null");
        List<WebElement> products = response.jsonPath().getList("products");
        Assert.assertFalse(products.isEmpty(), "Cart products should not be empty");
        System.out.println("PASSED: getCartById - cart has " + products.size() + " products");
    }
}

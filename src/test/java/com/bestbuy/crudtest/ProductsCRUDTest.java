package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest {
    static ValidatableResponse response;

    static String name = "Energizer - AAA Batteries (8-Pack)" + TestUtils.getRandomValue();
    static String type = "HardGood" + TestUtils.getRandomValue();
    static Double price = 5.49;
    static String upc = TestUtils.getRandomValue();
    static Double shipping = 4.99;
    static String description = "Compatible with select electronic devices; AAA size; 4-pack";
    static String manufacturer = "Energizer";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(201);
    }

    @Test
    public void test002() {
        Response response = given()
                .when()
                .get();

        response.then().statusCode(200);

        response.prettyPrint();
    }

    @Test
    public void test003() {
        String name = ProductsCRUDTest.name + "Updated";
        String type = ProductsCRUDTest.type + "Updated";
        Double price = 1700.00;
        String upc = TestUtils.getRandomValue();
        Double shipping = 7.99;
        String description = ProductsCRUDTest.description + "Updated";
        String manufacturer = ProductsCRUDTest.manufacturer + "Updated";
        String model = ProductsCRUDTest.model + "Updated";
        String url = ProductsCRUDTest.url + "Updated";
        String image = ProductsCRUDTest.image + "Updated";

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", 9999680)
                .when()
                .body(productPojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);

    }

    @Test
    public void test004() {
        given().log().ifValidationFails()
                .pathParam("id", 9999680)
                .when()
                .delete("/{id}")
                .then().log().ifValidationFails().statusCode(200);
        given()
                .pathParam("id", 9999680)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }

}
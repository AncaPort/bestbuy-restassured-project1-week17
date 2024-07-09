package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {
    static String storeName = TestUtils.getRandomValue() + "Ikea";
    static String storeType = "Furniture";
    static String address1 = "17, Wembley road";
    static String address2 = "";
    static String city = "London";
    static String state = "MN";
    static String zip = "55305";
    static double lat = 44.969658;
    static double lng = -93.449539;
    static String hours = "Mon: 11-9; Tue: 11-9; Wed: 11-9; Thurs: 11-9; Fri: 11-9; Sat: 10-9; Sun: 10-8";


    @Test
    public void test001() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(storeName);
        storePojo.setType(storeType);
        storePojo.setAddress(address1);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.then().log().ifValidationFails().statusCode(201);
        response.prettyPrint();
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
        String name = StoresCURDTest.storeName + "Updated";
        String type = StoresCURDTest.storeType + "Updated";
        String address = StoresCURDTest.address1 + "Updated";
        String address2 = StoresCURDTest.address2;
        String city = StoresCURDTest.city + "Updated";
        String state = StoresCURDTest.state + "Updated";
        String zip = TestUtils.getRandomValue();
        double lat = 44.898855;
        double lng = -36.556651;
        String hours = "Mon: 9-10; Tue: 9-10; Wed: 9-10; Thurs: 9-10; Fri: 9-10; Sat: 11-6; Sun: 12-5";

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", 18)
                .when()
                .body(storePojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);

    }

    @Test
    public void test004() {
        given().log().ifValidationFails()
                .pathParam("id", 8921)
                .when()
                .delete("/{id}")
                .then().log().ifValidationFails().statusCode(200);
        given()
                .pathParam("id", 8921)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }
}
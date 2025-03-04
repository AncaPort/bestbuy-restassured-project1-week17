package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }
    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String pName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + pName);
        System.out.println("------------------End of Test---------------------------");
    }
    //24. Extract the names of all the products
    @Test
    public void test024() {
        List<String> allPrdt = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the products is : " + allPrdt);
        System.out.println("------------------End of Test---------------------------");
    }
    //25. Extract the productId of all the products
    @Test
    public void test025() {
        List<Integer> allPrdtId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The productId of all the products is : " + allPrdtId);
        System.out.println("------------------End of Test---------------------------");
    }
    //26. Print the size of the data list
    @Test
    public void test026() {
        List<Integer> dataList = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + dataList.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4 Pack)
    @Test
    public void test027() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the product where product name = Energizer - MAX Batteries AA (4-pack) is : " + values);
        System.out.println("------------------End of Test---------------------------");
    }
    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2 Pack)
    @Test
    public void test028() {
        List<HashMap<String, ?>> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2 Pack) is : " + model);
        System.out.println("------------------End of Test---------------------------");
    }
    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<?> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of 8th products is : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {
        List<?> categoriesId = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the store where product id = 150115 is : " + categoriesId);
        System.out.println("------------------End of Test---------------------------");
    }
    //31. Get all the descriptions of all the products
    @Test
    public void test031() {
        List<?> desc = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of all the products is : " + desc);
        System.out.println("------------------End of Test---------------------------");
    }
    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<?> idOfProdct = response.extract().path("data.categories.findAll{it.id}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all the all categories of all the products is : " + idOfProdct);
        System.out.println("------------------End of Test---------------------------");
    }
    //33. Find the product names Where  type = HardGood
    @Test
    public void test033() {
        List<HashMap<String, ?>> prodctName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product names Where  type = HardGood is : " + prodctName);
        System.out.println("------------------End of Test---------------------------");
    }
    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Print Total Number Of Categories For Product Store 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)': " + values.size());
        System.out.println("------------------End of Test---------------------------");   }

    //35. Find the createdAt for all products whose price <  5.49
    @Test
    public void test035() {
        List<String> pricePrdt = response.extract().path("data.findAll{it.price < 5.49}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Print Total createdAt for all products whose price <  5.49 is: " + pricePrdt);
        System.out.println("------------------End of Test---------------------------");
    }
    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4 Pack)”
    @Test
    public void test036() {
        List<String> categryPrdt = response.extract().path("data.findAll{it.name.startsWith('Energizer - MAX Batteries AA (4-Pack)')}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The name of all categories Where product name = “Energizer - MAX Batteries AA (4 Pack) is: " + categryPrdt);
        System.out.println("------------------End of Test---------------------------");
    }
    //37. Find the manufacturer of all the products
    @Test
    public void test037() {
        List<String> manufacture = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The manufacturer of all the products is: " + manufacture);
        System.out.println("------------------End of Test---------------------------");
    }
    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test038() {
        List<?> imgManu = response.extract().path("data.findAll{it.manufacturer.startsWith('Energizer')}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The imge of products whose manufacturer is = Energizer is: " + imgManu);
        System.out.println("------------------End of Test---------------------------");
    }
    //39. Find the createdAt for all categories products whose price >  5.99
    @Test
    public void test039() {
        List<String> priceGreat = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all categories products whose price >  5.99 is: " + priceGreat);
        System.out.println("------------------End of Test---------------------------");
    }
    //40. Find the uri of all the products
    @Test
    public void test040() {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The uri of all the products is: " + url);
        System.out.println("------------------End of Test---------------------------");
    }
}

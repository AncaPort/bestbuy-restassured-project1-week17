package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> listAllStore = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the store is : " + listAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> listAllStoreId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store is : " + listAllStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<String> dataSize = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + dataSize.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store where store name = St Cloud is : " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store where store name = Rochester is : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<?> services = response.extract().path("data[8].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the services of 8th store is : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test10() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of the store where service name = Windows Store is : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test11() {
        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store is : " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test12() {
        List<Integer> allStoreId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all the store is : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where  state = ND
    @Test
    public void test13() {
        List<?> state = response.extract().path("data.findAll{it.state.startsWith('ND')}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where  state = ND is : " + state);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test14() {
        List<Integer> totalService = response.extract().path("data.findAll{it.name == 'Rochester'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name = Rochester is : " + totalService.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test15() {
        List<HashMap<String, ?>> nameService = response.extract().path("data.findAll{it.services.name == 'Windows Store'}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name = “Windows Store” is : " + nameService);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test16() {
        List<?> storeService = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name = “Fargo” is : " + storeService);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test17() {
        List<Integer> zipStore = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store is : " + zipStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test18() {
        List<Integer> zipStore1 = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville is : " + zipStore1);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test19() {
        List<String> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name='Magnolia Home Theater'}}.services.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater is : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test20() {
        List<Double> lat = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores is : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }
}

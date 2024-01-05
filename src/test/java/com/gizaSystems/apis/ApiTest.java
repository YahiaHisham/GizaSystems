package com.gizaSystems.apis;

import com.gizaSystems.apis.payloads.CreatePetPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {
    private static final String BASE_URI = "https://petstore.swagger.io/v2";

    public static JsonPath rawToJson(String response) {
        return new JsonPath(response);
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void verifyThatUserCanGetRandomPetById() {
        int petId = 10;
        String response = given()
                .log().all()
                .when().get("/pet/" + petId)
                .then().assertThat()
                .log().all()
                .statusCode(200)
                .extract().response().asString();
        JsonPath js1 = rawToJson(response);
        String petName = js1.getString("name");
        System.out.println("The Pet Name Is : " + petName);
    }

    @Test
    public void verifyThatUserCanGetPetsByStatus() {
        String status = "string";
        String response = given()
                .log().all()
                .queryParam("status", status)
                .when().get("/pet/findByStatus")
                .then().assertThat()
                .log().all()
                .statusCode(200)
                .extract().response().asString();

        JsonPath js1 = rawToJson(response);
        List<String> petNames = js1.getList("name");
        System.out.println("The Pet Name Is : " + petNames);
    }

    @Test
    public void verifyThatUserCanCreateNewPet() {
        String newPetJson = CreatePetPayload.createPet(22, "new Pet Name", "status");
        String response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(newPetJson)
                .when().post("/pet")
                .then().assertThat()
                .log().all()
                .statusCode(200)
                .extract().response().asString();

        System.out.println("The Response Body Is : " + response);
    }
}



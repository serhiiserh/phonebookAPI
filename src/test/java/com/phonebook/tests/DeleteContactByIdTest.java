package com.phonebook.tests;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTest extends TestBase {
    String id;

    @BeforeMethod

    public void precondition() {
        ContactDto contactDto = ContactDto.builder()
                .name("Yoda")
                .lastName("Sensei")
                .email("yodaSensei21@gmail.com")
                .phone("963741896123")
                .address("Venera")
                .description("the Best")
                .build();

        String message = given()
                .header(AUTH, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");
        //System.out.println(message);Contact was added! ID: 5147cf1f-d6b5-43a0-8a5b-d0a504e39b69
        String[] split = message.split(": ");
        id = split[1];
    }

    @Test

    public void deleteContactByIdSuccessTest() {
        given()
                .header(AUTH, TOKEN)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"));
    }

    @Test

    public void deleteContactByIdNegative401Test() {
        given()
                .header(AUTH, NOTOKEN)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message", equalTo("Malformed JWT JSON: &\u0016?r#?$?3#Sb'"));


    }

    @Test

    public void deleteContactByIdNegative500Test() {
        given()
                .header(AUTH, TOKEN)
                .delete("contacts/")
                .then()
                .assertThat().statusCode(500)
                .assertThat().body("message", equalTo("Request method 'DELETE' not supported"))
                .assertThat().contentType("application/json");


    }

    @Test

    public void deleteContactByIdNegative403Test() {
        given()
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(403)
                .assertThat().contentType("");


    }
}

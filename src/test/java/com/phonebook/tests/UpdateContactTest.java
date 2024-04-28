package com.phonebook.tests;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.ContactDto;
import com.phonebook.dto.UpdateContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateContactTest extends TestBase{
   String id;

    @BeforeMethod

    public void precondition() {
        ContactDto contactDto = ContactDto.builder()
                .id("6ece0835-9554-40e7-8096-85f2f186e55e")
                .name("Yodak")
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
        String[] split = message.split(": ");
        id = split[1];
    }

    @Test

    public void updateContactSuccessTest() {
        UpdateContactDto newContactDto = UpdateContactDto.builder()
                .id("6ece0835-9554-40e7-8096-85f2f186e55e")
                .name("Yodakio")
                .lastName("Sensei")
                .email("yodaSensei21@gmail.com")
                .phone("963741896123")
                .address("Venera")
                .description("the Best")
                .build();
        given()
                .header(AUTH, TOKEN)
                .body(newContactDto)
                .contentType(ContentType.JSON)
                .put("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was updated"));
    }

    @Test

    public void updateContactNegative400Test() {
        UpdateContactDto newContactDto = UpdateContactDto.builder()
                .name("Yodakio")
                .lastName("Sensei")
                .email("yodaSensei21@gmail.com")
                .phone("963741896123")
                .address("Venera")
                .description("the Best")
                .build();
        given()
                .header(AUTH, TOKEN)
                .body(newContactDto)
                .contentType(ContentType.JSON)
                .put("contacts")
                .then()
                .assertThat().statusCode(400);
                //.assertThat().body("message", equalTo("<{id=must not be blank}>"));
    }

    @Test

    public void updateContactNegative401Test() {
        UpdateContactDto newContactDto = UpdateContactDto.builder()
                .id("6ece0835-9554-40e7-8096-85f2f186e55e")
                .name("Yodakio")
                .lastName("Sensei")
                .email("yodaSensei21@gmail.com")
                .phone("963741896123")
                .address("Venera")
                .description("the Best")
                .build();
        given()
                .header(AUTH, NOTOKEN)
                .body(newContactDto)
                .contentType(ContentType.JSON)
                .put("contacts")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message", equalTo("Malformed JWT JSON: &\u0016?r#?$?3#Sb'"));
    }

    @Test

    public void updateContactNegative403Test() {
        UpdateContactDto newContactDto = UpdateContactDto.builder()
                .id("6ece0835-9554-40e7-8096-85f2f186e55e")
                .name("Yodakio")
                .lastName("Sensei")
                .email("yodaSensei21@gmail.com")
                .phone("963741896123")
                .address("Venera")
                .description("the Best")
                .build();
        given()
                .header(AUTH, TOKEN)
                .body(newContactDto)
                .contentType(ContentType.JSON)
                .put("contacts/contacts")
                .then()
                .assertThat().statusCode(403);
    }

}

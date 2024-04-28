package com.phonebook.tests;

import com.phonebook.dto.AllContactsDto;
import com.phonebook.dto.ContactDto;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllContactsTests extends TestBase {

    @Test

    public void getAllContactsSuccessTest() {
        AllContactsDto contactsDto = given()
                .header(AUTH, TOKEN)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(AllContactsDto.class);
        for (ContactDto contact : contactsDto.getContacts()) {
            System.out.println(contact.getId() + " *** " + contact.getName());
            System.out.println("=========================");

        }
    }

    @Test
    public void getAllContactsNegative403Test() {
        given()
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(403);

    }

    @Test
    public void getAllContactsNegative500Test() {
      given()
                .header(AUTH, TOKEN)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(500);

    }

    @Test
    public void getAllContactsNegative401Test() {
        given()
                .header(AUTH, NOTOKEN)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(401);
    }







}


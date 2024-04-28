package com.phonebook.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZ3JpZ29yeTEzMjRAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3MTQ2Mzg3NzcsImlhdCI6MTcxNDAzODc3N30.UcmJvqliShRkmqNmG-PQFvB4ldHhd5mmMtug1r2j48o";

    public static final String AUTH = "Authorization";

    public static final String NOTOKEN = "JhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZ3JpZ29yeTEzMjRAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3MTQ2Mzg3NzcsImlhdCI6MTcxNDAzODc3N30.UcmJvqliShRkmqNmG-PQFvB4ldHhd5mmMtug1r2j48o";

//    public static final String NOAUTH = "Authorizat";




    @BeforeMethod
    public void init(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;

/**
 *
 * @author rafael
 */
public class MyFirstRestTest {
    //The base URL for the library project
    private static final String BASE_URL = "http://localhost:8080/librarytest/rest/";
    
    public MyFirstRestTest() {
    }
    
    @Test
    public void testFetchBook(){
        //The specific resource that we want to test.
        String resourceName = "books/3";
        
        //1 - Making a request for the rest service, getting the body of the response and formatting the output. This way the answer will come as a xml
        //get(BASE_URL+resourceName).body().prettyPrint();
        
        //2 - Making the same request for the rest service and accepting only JSON as an answer and formatting it.
        //given().accept(ContentType.JSON).get(BASE_URL+resourceName).body().prettyPrint();
       
        //3 - Making the same request for the rest service and accepting only JSON as an answer and formatting it.        
        given().accept(ContentType.JSON).log().all().get(BASE_URL+resourceName).body().prettyPrint();
        
    }
  
}
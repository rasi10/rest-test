/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static org.junit.Assert.assertEquals;

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
       
        //3 - Making the same request for the rest service and accepting only JSON as an answer and formatting it. Adding also log information for the response.        
        //given().accept(ContentType.JSON).log().all().get(BASE_URL+resourceName).body().prettyPrint();
        
        //4 - Making the same request and saving the response on a variable Response for later manipulation. Also removing log information from the answer.
        //Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);        
        //System.out.println("Status code: "+ response.statusCode()); //printing the statuscode.
        //String title = response.body().jsonPath().getString("book.title");
        //System.out.println("Title: "+title);
        
        
        //5 - Making the same request as in 4, and also adding an assertion for the status code from the response. 
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);        
        System.out.println("Status code: "+ response.statusCode()); //printing the statuscode.
        assertEquals("Status code should be 200",200, response.statusCode());
        
    }
    
    @Test
    public void testFetchInvalidBookReturns404(){
        //The specific resource that we want to test. In this case, a book that we know that does not exist.
        String resourceName = "books/293872";
        
        
        //6 - Making the same request as in 5, but with a wrong book. Also using an assertion to 404 
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);        
        System.out.println("Status code: "+ response.statusCode()); //printing the statuscode.
        assertEquals("Status code should be 404",404, response.statusCode());
        
    }
  
}
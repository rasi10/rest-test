/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.UUID;
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
    
    @Test
    public void testAddNewBook(){
        String resourceName = "books";
        //7 - Make a post request to insert a book and include an assertion to assert that the creation was successfull.
        //With a static check for the assertion, which is not nice.
        //String postBody = ""
        //        + "{\n" +
        //        "\"book\":\n" +
        //        "  {\n" +
        //        "    \"description\":\"The novel focuses on a post-apocalyptic character named Snowman, living near a group of primitive human-like creatures whom he calls Crakers.\",\n" +
        //        "    \"isbn\":\"0-7710-0868-6\",\n" +
        //        "    \"nbOfPage\":411,\n" +
        //         "    \"title\":\"Oryx and Crake\"\n" +
        //        "  }\n" +
        //        "}";

        //Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL +resourceName);
        //assertEquals("Post response should have status code 201",201,postResponse.statusCode());
        
        //Response getResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName);
        //String fetchedTitle = getResponse.jsonPath().getString("books.book[-1].title"); //getting the last book created on the list with jsonPath
        //assertEquals ("Oryx and Crake", fetchedTitle);
                
        
        
        //8 - Make a post request to insert a book and include an assertion to assert that the creation was successfull.
        //Similar to 7, but in this version we are using values for title, description and isbn that are randomly generated.        
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();
               
        String postBodyTemplate = ""
                + "{\n" +
                "\"book\":\n" +
                "  {\n" +
                "    \"description\":\"%s\",\n" +
                "    \"isbn\":\"%s\",\n" +
                "    \"nbOfPage\":411,\n" +
                "    \"title\":\"%s\"\n" +
                "  }\n" +
                "}";
        
        String postBody = String.format(postBodyTemplate, description,isbn,title);

        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL +resourceName);
        assertEquals("Post response should have status code 201",201,postResponse.statusCode());
        
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL + resourceName).prettyPeek();
        String fetchedTitle = getResponse.jsonPath().getString("books.book[-1].title");
        String fetchedDescription = getResponse.jsonPath().getString("books.book[-1].description");
        String fetchedIsbn = getResponse.jsonPath().getString("books.book[-1].isbn");      
        
        assertEquals (title, fetchedTitle);
        assertEquals (description, fetchedDescription);
        assertEquals (isbn, fetchedIsbn);
    }
  
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;

/**
 *
 * @author rafael
 */
public class MyFirstRestTest {
    private static final String BASE_URL = "http://localhost:8080/librarytest/rest/";
    
    public MyFirstRestTest() {
    }
    
    @Test
    public void testFetchBook(){
        String resourceName = "books/3";
        get(BASE_URL+resourceName).body().prettyPrint();
        
    }
}

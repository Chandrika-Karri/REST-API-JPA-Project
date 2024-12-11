package com.example.restapijpaproject;

/**
 * In REST-API we are using CRUD operations. While creating the project with Jakarta EE we can choose
 * the dependencies under specifications so all the required dependencies are included. We create the project
 * with the required tasks to perform on the application, so the Annotations such as GET,POST,PUT,DELETE and many
 * other annotations can be directly imported in to the project.
 */

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * If you want to view the information in a specific page we can specify the path of that page and @GET annotation
 * is used to read and @Produces is used to view the output on the browser
 */
@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}
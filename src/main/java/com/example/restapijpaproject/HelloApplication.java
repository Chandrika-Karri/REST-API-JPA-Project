package com.example.restapijpaproject;

import jakarta.ws.rs.ApplicationPath; //Annotation which are part of pre-defined packages that has information and processed by compiler,frameworks,tools and runtime.
import jakarta.ws.rs.core.Application; //Application is a pre-defined class in jakarta library and HelloApplication inherits it.

@ApplicationPath("/api")
//This is our application path we use to deploy. We can use name that is relavent to our application.

/**  When we create our REST api along with Glassfish application server both HelloResource and HelloApplication classes are automatically
 * created. They are referred as basic templates those are auto generated at the time of project creation. As we can use below
 * the HelloApplication class inherits all the methods and attributes from the predefined Application class.  */
public class HelloApplication extends Application {

}
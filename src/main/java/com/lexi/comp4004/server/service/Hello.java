package com.lexi.comp4004.server.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>"
				+ "</html> ";
	}

	@GET
	@Path("index")
	@Produces(MediaType.TEXT_HTML)
	public String index() {
		return getFile("index.html");
	}
	
	@Context private HttpServletRequest request;

	@POST
	@Path("/authenticate")
	public String authenticate(@FormParam("username") String username, 
	        @FormParam("password") String password) {

	    // Implementation of your authentication logic
	    if (authenticate(username, password)) {
	        request.getSession(true);
	        // Set the session attributes as you wish
	    }
	}
	
	@GET
    @Produces("text/plain")
    public void hello(@Context HttpServletRequest req) {

        HttpSession session= req.getSession(true);
        Object foo = session.getAttribute("foo");
        if (foo!=null) {
            System.out.println(foo.toString());
        } else {
            foo = "bar";
            session.setAttribute("foo", "bar");
        }
        session.getSessionContext().

    }

	private String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");
		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	@POST
	@Path("test")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String login(HashMap<String, Object> params) {
		System.out.println(params);
		return "<html> " + "<title>" + "Test" + "</title>" + "<body><h1>" + params.toString() + "</body></h1>"
				+ "</html> ";
	}

}
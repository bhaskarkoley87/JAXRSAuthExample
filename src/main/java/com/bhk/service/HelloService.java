package com.bhk.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloService {
	
	String strauthorization ="";
	String[] values;
	
	String strUsername;
	String strPassword;
	
	@GET
	@Path("/")
	public Response getMessage(@Context HttpHeaders httpHeaders) {
		Set<String> headerKeys = httpHeaders.getRequestHeaders().keySet();
		
		
        for(String header:headerKeys){
        	if(header.equalsIgnoreCase("authorization")) {
        		strauthorization = httpHeaders.getRequestHeader(header).get(0);;
        	}
            //System.out.println(header+":"+httpHeaders.getRequestHeader(header).get(0));
        }
        
        if (!strauthorization.trim().equals("") && strauthorization.toLowerCase().startsWith("basic")) {
			
		    // Authorization: Basic base64credentials
		    String base64Credentials = strauthorization.substring("Basic".length()).trim();
		    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		    // credentials = username:password
		    values = credentials.split(":", 2);
		    for(String val : values) {
		    	System.out.println(val);
		    }
        }
        
        if(values != null && values.length > 0) {
			strUsername = values[0];
			strPassword = values[1];
		}else {
			strUsername = "";
			strPassword = "";
		}
        
        
        if(strUsername.equalsIgnoreCase("bhaskar") && strPassword.equalsIgnoreCase("password")) {
        	return Response.status(200).entity("REST webservice is working fine...").build();
		}else {
			return Response.status(400).entity("Invalid User").build();
		}
        
        
		
	}

}

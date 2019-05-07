package com.bhk.service.publicher;

import java.util.HashMap;
import java.util.Map;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;


public class WebServicePublisher {

	public static void main(String[] args) {        
        final String baseUri = "http://localhost:7080/";
        final Map<String, String> initParams = new HashMap<String, String>();

        // Register the package that contains your javax.ws.rs-annotated beans here
        initParams.put("com.sun.jersey.config.property.packages","com.bhk.service");

        System.out.println("Starting grizzly...");
        try {
            SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
            System.out.println(String.format("Jersey started with WADL "
                    + "available at %sapplication.wadl.", baseUri));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}

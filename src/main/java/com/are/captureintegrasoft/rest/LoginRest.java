/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.are.captureintegrasoft.rest;

import com.are.captureintegrasoft.request.LoginRequest;
import com.are.captureintegrasoft.response.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 *
 * @author ARE
 */
public class LoginRest {
        
    private String url;
    
    public LoginRest(String url) {
        this.url = url;
    }
    
    public LoginResponse login(LoginRequest loginRequest) {
    	
    	LoginResponse loginResponse = null;
    	
		try {
			
			Gson gson = new Gson();  

			String jsonLogin = gson.toJson(loginRequest);
			System.out.println("Login: " + jsonLogin);
			String charset = "UTF-8";      		
			URL url = new URL(this.url);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestProperty("Accept-Charset", charset);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			
			try(OutputStream os = (OutputStream) con.getOutputStream()) {
			    byte[] input = jsonLogin.getBytes("utf-8");
			    os.write(input, 0, input.length);           
			}			
			
			if (con.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + con.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(con.getInputStream(), charset);
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
                 
            GsonBuilder gsonBuilder = new GsonBuilder();
            /*
            gsonBuilder.registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter());
            gsonBuilder.registerTypeAdapter(Date.class, new DateConverterTypeAdapter());
            */
            Gson gson1 = gsonBuilder.create();
            
            loginResponse = gson1.fromJson(sb.toString(), LoginResponse.class);
            
            con.disconnect();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return loginResponse;
    }   
    
}

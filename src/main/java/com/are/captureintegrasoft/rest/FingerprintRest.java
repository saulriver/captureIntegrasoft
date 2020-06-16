/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.are.captureintegrasoft.rest;

import com.are.captureintegrasoft.ByteArrayToBase64TypeAdapter;
import com.are.captureintegrasoft.request.FingerprintRequest;
import com.are.captureintegrasoft.response.FingerprintResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author ARE
 */
public class FingerprintRest {
    
    private String url;
    
    public FingerprintRest(String url) {
        this.url = url;
    }
    
    public FingerprintResponse saveFingerprintEmployee(FingerprintRequest fingerprintRequest) throws MalformedURLException, IOException {
        
        FingerprintResponse fingerprintResponse = null;
        
        Gson gson = new Gson();    

        String JSON = gson.toJson(fingerprintRequest);
        
        System.out.println("Json: " + JSON);
       
        try {
            String charset = "UTF-8";            
            URL url = new URL(this.url);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");            
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = JSON.getBytes("utf-8");
                os.write(input, 0, input.length);           
            }
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream(), charset);
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            
            Gson gson1 = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter()).create();
            fingerprintResponse = gson1.fromJson(sb.toString(), FingerprintResponse.class);
            System.out.println("Result: " + fingerprintResponse.isResult());
            System.out.println("Error: " + fingerprintResponse.isError());
            System.out.println("Message: " + fingerprintResponse.getMessage());
            conn.disconnect();
            
        } catch (RuntimeException e) {
            System.out.println("Exception in Client RestFul Employee:- " + e);
        }
       
        return fingerprintResponse;
    }
}

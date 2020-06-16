/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.are.captureintegrasoft.rest;

import com.are.captureintegrasoft.ByteArrayToBase64TypeAdapter;
import com.are.captureintegrasoft.entity.Employee;
import com.are.captureintegrasoft.request.EmployeeRequest;
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
public class EmployeeRest {
    
    private String url;
    
    public EmployeeRest(String url) {
        this.url = url;
    }
    
    public Employee findEmployee(EmployeeRequest employeeRequest) throws MalformedURLException, IOException {
        
        Employee employee = null;
        
        Gson gson = new Gson();    
         
        String JSON = gson.toJson(employeeRequest);
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
            employee = gson1.fromJson(sb.toString(), Employee.class);
            
            if (employee != null) {
                System.out.println("Employee found !");
            }else{
                System.out.println("Employee not found !");
            }
            conn.disconnect();
            
        } catch (RuntimeException e) {
            System.out.println("Exception in Client RestFul Employee:- " + e);
        }
        
        return employee;
    }    

    public Employee findEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

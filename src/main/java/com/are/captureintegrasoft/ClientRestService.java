/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.are.captureintegrasoft;

import com.are.captureintegrasoft.entity.Employee;
import com.are.captureintegrasoft.request.FingerprintRequest;
import com.are.captureintegrasoft.response.FingerprintResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import org.json.JSONObject;

/**
 *
 * @author Soluciones
 */
public class ClientRestService {

    private String url;
    
    public ClientRestService(String url) {
        this.url = url;
    }
    
    public Employee consumeRestEmployee() throws MalformedURLException, IOException {
        Employee employee = null;
        try {
            String charset = "UTF-8";            
            URL url = new URL(this.url);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Accept", "application/json");
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
            
//            employee = new Employee();
//            JSONObject obj = new JSONObject(sb.toString());
//            employee.setId(obj.getString("id"));
//            employee.setName(obj.getString("name"));
//            employee.setAddress(obj.getString("address"));
//            employee.setCity(obj.getString("city"));
//            employee.setPhoneNumber(obj.getString("phoneNumber"));
//            employee.setMail(obj.getString("mail"));
//            System.out.println("FingerPrint: " + obj.getString("fingerprint"));
//            employee.setFingerprint(obj.getString("fingerprint").getBytes("UTF8"));
//            System.out.println("Photo: " + obj.getString("photo"));
//            employee.setPhoto(obj.getString("photo").getBytes("UTF8"));
//            JSONObject objDepartment = obj.getJSONObject("department");
//            JSONObject objOffice = obj.getJSONObject("office");
//            JSONObject objZone = obj.getJSONObject("zone");
//            
//            Department dpto = new Department();
//            dpto.setId(objDepartment.getLong("id"));
//            dpto.setName(objDepartment.getString("name"));
//            dpto.setState(objDepartment.getInt("state"));
//            
//            employee.setDepartment(dpto);
//            
//            Office office = new Office();
//            office.setId(objOffice.getLong("id"));
//            office.setName(objOffice.getString("name"));
//            office.setState(objOffice.getInt("state"));
//            
//            employee.setOffice(office);
//            
//            Zone zone = new Zone();
//            zone.setId(objZone.getLong("id"));
//            zone.setName(objZone.getString("name"));
//            zone.setState(objZone.getInt("state"));
//            
//            employee.setZone(zone);

            Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter()).create();
            employee = gson.fromJson(sb.toString(), Employee.class);
            System.out.println("ID: " + employee.getId());
            System.out.println("Code: " + employee.getCode());
            System.out.println("Name: " + employee.getFirstName());
            conn.disconnect();
            
        } catch (RuntimeException e) {
            System.out.println("Exception in Client RestFul Employee:- " + e);
        }
        
        return employee;
    }
    
    public FingerprintResponse consumeRestSaveFingerprintEmployee(FingerprintRequest fingerprintRequest) throws MalformedURLException, IOException {
        
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
    
    public InputStream getImageEmployee(String url_image) throws IOException {
        URL url = new URL(url_image);
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        return in;
    }
    
//    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
//        @Override
//        public byte[] deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            return Base64.getDecoder().decode((json.getAsString()));
//        }
//        @Override
//        public JsonElement serialize(byte[] src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
//            return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
//        }
//
//       
//    }

    void setToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

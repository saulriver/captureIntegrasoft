/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.are.captureintegrasoft;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.util.Base64;

/**
 *
 * @author Soluciones
 */
public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

    @Override
    public byte[] deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Base64.getDecoder().decode((json.getAsString()));
    }

    @Override
    public JsonElement serialize(byte[] src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
    }

}

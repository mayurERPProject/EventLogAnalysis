package com.cs.dpg.assignment.application.utility;

import java.io.IOException;
import java.io.UncheckedIOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ConvertJSONToObject {

	public static <T> T convertJsonStringToObject(String string, Class<T> pojo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(string, pojo);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

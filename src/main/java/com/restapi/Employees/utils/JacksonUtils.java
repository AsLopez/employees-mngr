package com.restapi.Employees.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JacksonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtils.class);

    private static final String JSON_MAPPING = "JSONMappingException";

    private JacksonUtils() {
    }

    public static JsonNode parseWithoutNulls(Object objectToParse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readTree(mapper.writeValueAsString(objectToParse));
    }

    public static String getPlainJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error(JSON_MAPPING, e);
        }
        return json;
    }

    public static String getPlainJsonNotNulls(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            LOGGER.error(JSON_MAPPING, e);
        }
        return json;
    }

    public static String getPrettyJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

        } catch (JsonProcessingException e) {
            LOGGER.error(JSON_MAPPING, e);
        }
        return json;
    }

    public static String getPrettyJsonNotNulls(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = null;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

        } catch (JsonProcessingException e) {
            LOGGER.error(JSON_MAPPING, e);
        }
        return json;
    }

    public static JsonNode toJsonOrNull(String str) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(str);
        } catch (IOException var3) {
            return null;
        }
    }

}


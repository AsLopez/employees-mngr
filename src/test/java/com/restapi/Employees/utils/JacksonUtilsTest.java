package com.restapi.Employees.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JacksonUtilsTest {

    @Test
    void testParseWithoutNulls() throws Exception {
        JsonNode result = JacksonUtils.parseWithoutNulls("objectToParse");
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetPlainJson() {
        String result = JacksonUtils.getPlainJson("object");
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetPlainJsonNotNulls() {
        String result = JacksonUtils.getPlainJsonNotNulls("object");
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetPrettyJson() {
        String result = JacksonUtils.getPrettyJson("object");
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetPrettyJsonNotNulls() {
        String result = JacksonUtils.getPrettyJsonNotNulls("object");
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetToJsonOrNull_Json() {
        JsonNode result = JacksonUtils.toJsonOrNull("{\"val\": \"test\"}");
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetToJsonOrNull_Null() {
        JsonNode result = JacksonUtils.toJsonOrNull("objectToParse");
        Assertions.assertNull(result);
    }

}
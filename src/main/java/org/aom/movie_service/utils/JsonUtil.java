package org.aom.movie_service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Singleton-style provider for configured ObjectMapper.
 */
public class JsonUtil {
    // Thread-safe singleton instance
    // A new ObjectMapper instance is not created each time JsonUtil.getObjectMapper() is called.
    // The OBJECT_MAPPER is a static final field, initialized only once when the class is loaded.
    // All calls to getObjectMapper() return this same instance,
    // ensuring singleton-like behavior for the ObjectMapper.

    private static final ObjectMapper OBJECT_MAPPER = createConfiguredMapper();

    private JsonUtil() {} // Prevent instantiation

    private static ObjectMapper createConfiguredMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
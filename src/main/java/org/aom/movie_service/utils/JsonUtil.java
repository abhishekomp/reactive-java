package org.aom.movie_service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Singleton-style provider for configured ObjectMapper.
 */
public class JsonUtil {
    // Thread-safe singleton instance
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
package com.khadimullin.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Json {
    public Map<String, String> parseJson(String json) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        map.put("description", jsonNode.get("weather").get(0).get("description").asText());
        map.put("temp", jsonNode.get("main").get("temp").asText());
        map.put("name", jsonNode.get("name").asText());

        return map;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author luis.renteria
 */
public class Handle500 {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode loadJsonFromFile(String filePath) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + filePath);

        return objectMapper.readTree(file);
    }
}

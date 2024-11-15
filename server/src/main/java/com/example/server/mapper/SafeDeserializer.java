/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.mapper;

/**
 *
 * @author luis.renteria
 */
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SafeDeserializer<T> extends JsonDeserializer<T> {

    private static final Logger LOGGER = Logger.getLogger(SafeDeserializer.class.getName());

    // Estos campos se inicializan de manera predeterminada en un constructor sin argumentos
    private Class<T> clazz;
    private String fieldName;

    // Constructor sin argumentos
    public SafeDeserializer() {
        // Este constructor es necesario para Jackson
    }

    // Método para configurar el deserializador
    public void configure(Class<T> clazz, String fieldName) {
        this.clazz = clazz;
        this.fieldName = fieldName;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            JsonNode node = p.getCodec().readTree(p);
            // Procesar el JSON de acuerdo al tipo de dato esperado
            if (node instanceof TextNode) {
                return (T) node.asText();  // Para un String (este es solo un ejemplo)
            }
            return null;
        } catch (JsonProcessingException ex) {
            // Loggear el error con el nombre de la clase y el atributo
            LOGGER.log(Level.SEVERE, "Error al deserializar atributo: " + fieldName + " en clase: " + clazz.getSimpleName(), ex);
            throw ex;  // Propagar la excepción
        }
    }
}

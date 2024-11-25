/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.server.mapper;

/**
 *
 * @author luis.renteria
 */
import java.lang.reflect.Field;

public class TreePrinter {

    public static void printClassDetailsForClass(Class<?> clazz) {

        System.out.println("Class: " + clazz.getSimpleName());

        for (Field field : clazz.getDeclaredFields()) {

            Class<?> fieldType = field.getType();

            if (fieldType.isArray()) {

                Class<?> componentType = fieldType.getComponentType();
                System.out.println("  " + field.getName() + " : " + componentType.getSimpleName() + "[]");
                
            } else {

                System.out.println("  " + field.getName() + " : " + fieldType.getSimpleName());
            }
        }
    }

}

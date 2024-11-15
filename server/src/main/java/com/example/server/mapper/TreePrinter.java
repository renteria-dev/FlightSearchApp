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
import java.util.HashSet;
import java.util.Set;

public class TreePrinter {

    // Usamos un Set para realizar un seguimiento de las clases a imprimir
    private static final Set<Class<?>> classesToPrint = new HashSet<>();

    // Usamos un Set para realizar un seguimiento de las clases ya impresas
    private static final Set<Class<?>> printedClasses = new HashSet<>();

    // Nombre del paquete que queremos permitir
    private static final String ALLOWED_PACKAGE_PREFIX = "com.example.server";

    // Paso 1: Recolectar todas las clases y sus atributos
    public static void collectClasses(Class<?> clazz) {
        // Verificamos si la clase pertenece al paquete permitido
        if (!clazz.getPackage().getName().startsWith(ALLOWED_PACKAGE_PREFIX)) {
            return;  // Si no pertenece al paquete permitido, no procesamos la clase
        }

        // Si la clase ya ha sido recolectada, no la volvemos a agregar
        if (classesToPrint.contains(clazz)) {
            return;
        }

        // Marcar la clase como recolectada
        classesToPrint.add(clazz);

        // Recorremos los campos (atributos) de la clase
        for (Field field : clazz.getDeclaredFields()) {
            // Obtener el tipo de la clase del campo
            Class<?> fieldType = field.getType();

            // Si el campo es un arreglo, tratamos el tipo de los elementos
            if (fieldType.isArray()) {
                // Si es un arreglo, solo añadimos el tipo del elemento del arreglo a la lista
                Class<?> componentType = fieldType.getComponentType();
                collectClasses(componentType);
            } else {
                // Si no es un arreglo, simplemente añadimos el tipo del campo
                if (!fieldType.isPrimitive() && fieldType.getPackage() != null && !fieldType.getPackage().getName().startsWith("java.lang")) {
                    collectClasses(fieldType);  // Llamamos recursivamente para explorar el tipo de la clase
                }
            }
        }

        // Buscar clases anidadas en la propia clase y agregarlas
        for (Class<?> nestedClass : clazz.getDeclaredClasses()) {
            collectClasses(nestedClass);
        }
    }

    // Imprimir detalles de una clase específica (sin recursividad aquí)
    public static void printClassDetailsForClass(Class<?> clazz) {
        if (printedClasses.contains(clazz)) {
            return;
        }

        printedClasses.add(clazz);

        // Imprimir el nombre de la clase
        System.out.println("Class: " + clazz.getSimpleName());

        // Primero, recorremos los campos (atributos) de la clase
        for (Field field : clazz.getDeclaredFields()) {
            // Obtener el tipo de la clase del campo
            Class<?> fieldType = field.getType();

            // Verificamos si el campo es un arreglo
            if (fieldType.isArray()) {
                // Si es un arreglo, solo imprimimos el tipo del elemento del arreglo
                Class<?> componentType = fieldType.getComponentType();
                System.out.println("  " + field.getName() + " : " + componentType.getSimpleName() + "[]");
            } else {
                // Si no es un arreglo, simplemente imprimimos el tipo del campo
                System.out.println("  " + field.getName() + " : " + fieldType.getSimpleName());
            }
        }
    }

}

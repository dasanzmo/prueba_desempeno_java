package com.riwi.prueba_desempeno.util.message;

public class ErrorMessage {

    public static String idNotFound(String entity) {
        final String message = "No %s registered with that id";
        return String.format(message, entity);
    }

}

package com.ndrlslz.restest.function

import io.restassured.response.Response

abstract class Function {
    static final String DELIMITER = ","

    abstract boolean match(String function)

    abstract void execute(Response response, String path, String value)

    static Integer[] getParametersAsInteger(String value) {
        String[] parameters = value.split(DELIMITER)

        Integer[] result = Arrays.asList(parameters)
                .stream()
                .map({ parameter -> Integer.valueOf(parameter.trim()) })
                .toArray() as Integer[]
        result
    }

    static String[] getParametersAsString(String value) {
        String[] parameters = value.split(DELIMITER)

        String[] result = Arrays.asList(parameters)
                .stream()
                .map({ parameter -> parameter.trim() })
                .toArray()
        result
    }
}
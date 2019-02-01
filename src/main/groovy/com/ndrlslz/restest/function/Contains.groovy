package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.containsInAnyOrder

class Contains extends Function {
    private static final String FUNCTION_CONTAINS = "contains"

    @Override
    boolean match(String function) {
        return function == FUNCTION_CONTAINS
    }

    @Override
    void execute(Response response, String path, String value) {
        try {
            Integer[] result = getParametersAsInteger(value)

            response.then().body(path, containsInAnyOrder(result))
        } catch (Exception ignored) {
            String[] stringResult = getParametersAsString(value)

            response.then().body(path, containsInAnyOrder(stringResult))
        }
    }
}

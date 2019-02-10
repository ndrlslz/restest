package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.hasItems

class HasItems extends Function {
    private static final String FUNCTION_HAS_ITEMS = "hasItems"

    @Override
    boolean match(String function) {
        return function == FUNCTION_HAS_ITEMS
    }

    @Override
    void execute(Response response, String path, String value) {
        try {
            Integer[] result = getParametersAsInteger(value)

            response.then().body(path, hasItems(result))
        } catch (Exception ignored) {
            String[] stringResult = getParametersAsString(value)

            response.then().body(path, hasItems(stringResult))
        }
    }
}

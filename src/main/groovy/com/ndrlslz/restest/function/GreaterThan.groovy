package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.greaterThan

class GreaterThan extends Function {
    private static final String FUNCTION_GREATER_THAN = "greaterThan"

    @Override
    boolean match(String function) {
        return function == FUNCTION_GREATER_THAN
    }

    @Override
    void execute(Response response, String path, String value) {
        response.then().body(path, greaterThan(Integer.valueOf(value)))
    }
}

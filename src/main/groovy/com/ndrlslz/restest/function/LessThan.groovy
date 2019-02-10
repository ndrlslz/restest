package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.lessThan

class LessThan extends Function {
    private static final String FUNCTION_LESS_THAN = "lessThan"

    @Override
    boolean match(String function) {
        return function == FUNCTION_LESS_THAN
    }

    @Override
    void execute(Response response, String path, String value) {
        response.then().body(path, lessThan(Integer.valueOf(value)))
    }
}

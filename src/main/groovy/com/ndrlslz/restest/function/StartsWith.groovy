package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.hasToString
import static org.hamcrest.Matchers.startsWith

class StartsWith extends Function {
    private static final String FUNCTION_STARTS_WITH = "startsWith"

    @Override
    boolean match(String function) {
        return function == FUNCTION_STARTS_WITH
    }

    @Override
    void execute(Response response, String path, String value) {
        response.then().body(path, hasToString(startsWith(value)))
    }
}

package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasToString

class ContainsString extends Function {
    public static final String FUNCTION_CONTAINS_STRING = "containsString"

    @Override
    boolean match(String function) {
        return function == FUNCTION_CONTAINS_STRING
    }

    @Override
    void execute(Response response, String path, String value) {
        response.then().body(path, hasToString(containsString(value)))

    }
}

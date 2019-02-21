package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.endsWith
import static org.hamcrest.Matchers.hasToString

class EndsWith extends Function {
    private static final String FUNCTION_ENDS_WITH = "endsWith"

    @Override
    boolean match(String function) {
        return function == FUNCTION_ENDS_WITH
    }

    @Override
    void execute(Response response, String path, String value) {
        response.then().body(path, hasToString(endsWith(value)))
    }
}

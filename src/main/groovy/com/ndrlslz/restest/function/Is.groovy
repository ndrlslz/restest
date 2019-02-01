package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.hasToString

class Is extends Function {
    public static final String FUNCTION_IS = "is"

    @Override
    boolean match(String function) {
        return function == FUNCTION_IS
    }

    @Override
    void execute(Response response, String path, String value) {
        response.then().body(path, hasToString(equalTo(value)))
    }
}


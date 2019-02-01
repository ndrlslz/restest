package com.ndrlslz.restest.function

import io.restassured.response.Response

class Is implements Function {
    public static final String FUNCTION_IS = "is"

    @Override
    boolean match(String function) {
        return function == FUNCTION_IS
    }

    @Override
    void execute(Response response, String path, String parameters) {

    }
}


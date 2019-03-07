package com.ndrlslz.restest.function

import io.restassured.response.Response

import static org.hamcrest.Matchers.isEmptyOrNullString

class IsEmptyOrNullString extends Function {
    private static final String FUNCTION_HAS_ITEMS = "isEmptyOrNullString"

    @Override
    boolean match(String function) {
        return function == FUNCTION_HAS_ITEMS
    }

    @Override
    void execute(Response response, String path, String value) {
        response.then().body(path, isEmptyOrNullString())
    }
}

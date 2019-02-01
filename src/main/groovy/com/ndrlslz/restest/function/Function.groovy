package com.ndrlslz.restest.function

import io.restassured.response.Response

interface Function {
    boolean match(String function)

    void execute(Response response, String path, String parameters)
}
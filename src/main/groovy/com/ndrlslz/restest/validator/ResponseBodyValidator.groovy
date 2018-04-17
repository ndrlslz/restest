package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

import static org.hamcrest.core.IsEqual.equalTo

class ResponseBodyValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        def validatableResponse = response.then()
        def jsonPath = response.jsonPath()
        if (scenarios.responseBody) {
            scenarios.responseBody.each { path, expectedValue ->
                try {
                    validatableResponse.body(path, equalTo(expectedValue))
                    println("Expect $path is $expectedValue")
                } catch (AssertionError ignored) {
                    println("Expect $path is $expectedValue, but actually is ${jsonPath.get(path)}")
                    success = false

                }
            }
        }
        success
    }
}
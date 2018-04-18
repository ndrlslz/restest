package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

import static org.hamcrest.core.IsEqual.equalTo

class ResponseBodyValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        if (scenarios.responseBody) {
            scenarios.responseBody.each { path, expectedValue ->
                try {
                    response.then().body(path, equalTo(expectedValue))
                    println("Expected $path is $expectedValue")
                } catch (AssertionError ignored) {
                    println("Expected $path is $expectedValue, but actually is ${response.jsonPath().get(path)}")
                    success = false
                } catch (Exception exception) {
                    println("Expected $path is $expectedValue, but exception is ${exception.class}: ${exception.message}")
                    success = false
                }
            }
        }
        success
    }
}
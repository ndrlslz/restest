package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red
import static org.hamcrest.core.IsEqual.equalTo

class ResponseBodyValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        if (scenarios.responseBody) {
            scenarios.responseBody.each { path, expectedValue ->
                try {
                    response.then().body(path, equalTo(expectedValue))
                    green("Expected $path is $expectedValue")
                } catch (AssertionError ignored) {
                    red("Expected $path is $expectedValue, but actually is ${response.jsonPath().get(path)}")
                    success = false
                } catch (Exception exception) {
                    red("Expected $path is $expectedValue, but exception is ${exception.class}: ${exception.message}")
                    success = false
                }
            }
        }
        success
    }
}
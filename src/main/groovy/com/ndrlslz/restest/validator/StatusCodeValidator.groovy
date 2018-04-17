package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

class StatusCodeValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        if (scenarios.statusCode) {
            def validatableResponse = response.then()
            try {
                validatableResponse.statusCode(scenarios.statusCode)
                println("Excepted status code is $scenarios.statusCode")
            } catch (AssertionError ignored) {
                println("Excepted status code is $scenarios.statusCode, but actually is $response.statusCode")
                success = false
            }
        }
        success
    }
}

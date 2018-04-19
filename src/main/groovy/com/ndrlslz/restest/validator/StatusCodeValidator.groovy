package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red

class StatusCodeValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        if (scenarios.statusCode) {
            try {
                response.then().statusCode(scenarios.statusCode)
                green("Excepted status code is $scenarios.statusCode")
            } catch (AssertionError ignored) {
                red("Excepted status code is $scenarios.statusCode, but actually is $response.statusCode")
                success = false
            } catch (Exception exception) {
                red("Expected status code is $scenarios.statusCode, but exception is ${exception.class}: ${exception.message}")
                success = false
            }
        }
        success
    }
}

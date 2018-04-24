package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red

class StatusCodeValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        try {
            response.then().statusCode(scenarios.expect.statusCode)
            green("Except status code is $scenarios.expect.statusCode")
        } catch (AssertionError ignored) {
            red("Except status code is $scenarios.expect.statusCode, but actually is $response.statusCode")
            success = false
        } catch (Exception exception) {
            red("Expect status code is $scenarios.expect.statusCode, but exception is ${exception.class}: ${exception.message}")
            success = false
        }
        success
    }
}

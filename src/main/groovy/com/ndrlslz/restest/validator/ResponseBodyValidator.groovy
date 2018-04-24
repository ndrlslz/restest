package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import com.ndrlslz.restest.utils.TemplateUtils
import io.restassured.response.Response

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasToString

class ResponseBodyValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        if (scenarios.responseBody) {
            scenarios.responseBody.each { path, expectedValue ->
                String value = TemplateUtils.render(expectedValue)
                try {
                    response.then().body(path, hasToString(containsString(value)))
                    green("Expect $path is $value")
                } catch (AssertionError ignored) {
                    red("Expect $path is $value, but actually is ${response.jsonPath().get(path)}")
                    success = false
                } catch (Exception exception) {
                    red("Expect $path is $value, but exception is ${exception.class}: ${exception.message}")
                    success = false
                }
            }
        }
        success
    }
}
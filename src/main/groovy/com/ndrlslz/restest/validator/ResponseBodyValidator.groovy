package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red
import static com.ndrlslz.restest.utils.TemplateUtils.render
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasToString

class ResponseBodyValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        scenarios.expect.body.each { expectedPath, expectedValue ->
            String value = render(expectedValue)
            String path = render(expectedPath)
            try {
                response.then().body(path, hasToString(containsString(value)))
                green("Expect body $path is $value")
            } catch (AssertionError ignored) {
                red("Expect body $path is $value, but actually is ${response.jsonPath().get(path)}")
                success = false
            } catch (Exception exception) {
                red("Expect body $path is $value, but exception is ${exception.class}: ${exception.message}")
                success = false
            }
        }
        success
    }
}
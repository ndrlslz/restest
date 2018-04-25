package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import com.ndrlslz.restest.utils.TemplateUtils
import io.restassured.response.Response

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasToString

class ResponseHeaderValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        scenarios.expect.headers.each { key, expectedValue ->
            String value = TemplateUtils.render(expectedValue)
            try {
                response.then().header(key, hasToString(containsString(value)))
                green("Expect header $key is $value")
            } catch (AssertionError ignored) {
                red("Expect header $key is $value, but actually is ${response.getHeader(key)}")
                success = false
            } catch (Exception exception) {
                red("Expect header $key is $value, but exception is ${exception.class}: ${exception.message}")
                success = false
            }
        }
        success
    }
}

package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import groovy.text.SimpleTemplateEngine
import io.restassured.response.Response

import static com.ndrlslz.restest.core.AppContext.retrieveCurrentVariables
import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red
import static org.hamcrest.core.IsEqual.equalTo

class ResponseBodyValidator implements Validator {
    private static SimpleTemplateEngine engine = new SimpleTemplateEngine()

    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        if (scenarios.responseBody) {
            scenarios.responseBody.each { path, expectedValue ->
                def value = engine.createTemplate(expectedValue.toString()).make(retrieveCurrentVariables())
                try {
                    response.then().body(path, equalTo(expectedValue))
//                    response.then().body(path, containsString(expectedValue as String))
//                    response.then().body(path, equalTo(engine.createTemplate(expectedValue.toString()).make(retrieveCurrentVariables())))
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
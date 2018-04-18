package com.ndrlslz.restest.handle

import com.ndrlslz.restest.client.RestClient
import com.ndrlslz.restest.model.Scenarios
import com.ndrlslz.restest.validator.ResponseBodyValidator
import com.ndrlslz.restest.validator.StatusCodeValidator
import com.ndrlslz.restest.validator.Validator
import io.restassured.response.Response

import java.util.stream.Collectors

import static java.util.stream.Collectors.toList

class ScenariosExecutor {
    private static List<Validator> validators

    static {
        validators = new ArrayList<>()
        validators.add(new StatusCodeValidator())
        validators.add(new ResponseBodyValidator())
    }

    static void exec(Scenarios scenarios) {
        println("\nRunning scenarios '$scenarios.name'")
        Response response = RestClient.get(scenarios)

        boolean fail = validate(scenarios, response)
        println(fail)
    }

    static boolean validate(Scenarios scenarios, Response response) {
        validators
                .stream()
                .map({ it.validate(response, scenarios) })
                .collect(toList())
                .stream()
                .any { !it }
    }
}

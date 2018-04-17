package com.ndrlslz.restest.handle

import com.ndrlslz.restest.client.RestClient
import com.ndrlslz.restest.model.Scenarios
import com.ndrlslz.restest.validator.ResponseBodyValidator
import com.ndrlslz.restest.validator.StatusCodeValidator
import com.ndrlslz.restest.validator.Validator
import io.restassured.response.Response

import java.util.stream.Collectors

class ScenariosExecutor {
    static void exec(Scenarios scenarios) {
        println("Begin scenarios: $scenarios.name")
        Response response = RestClient.get(scenarios)

        boolean fail = validate(scenarios, response)
        println(fail)
    }

    static boolean validate(Scenarios scenarios, Response response) {
        List<Validator> validators = new ArrayList<>()
        validators.add(new StatusCodeValidator())
        validators.add(new ResponseBodyValidator())

        validators
                .stream()
                .map({ it.validate(response, scenarios) })
                .collect(Collectors.toList())
                .stream()
                .any { !it }
    }
}

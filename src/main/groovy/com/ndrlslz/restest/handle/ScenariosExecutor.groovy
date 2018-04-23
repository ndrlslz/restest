package com.ndrlslz.restest.handle

import com.ndrlslz.restest.client.RestClient
import com.ndrlslz.restest.model.Scenarios
import com.ndrlslz.restest.store.VariablesStore
import com.ndrlslz.restest.validator.ResponseBodyValidator
import com.ndrlslz.restest.validator.StatusCodeValidator
import com.ndrlslz.restest.validator.Validator
import io.restassured.response.Response

import static com.ndrlslz.restest.report.ResultReport.result
import static com.ndrlslz.restest.utils.Printer.yellow
import static java.util.stream.Collectors.toList

class ScenariosExecutor {
    private static List<Validator> validators
    private static VariablesStore variablesStore

    static {
        validators = new ArrayList<>()
        validators.add(new StatusCodeValidator())
        validators.add(new ResponseBodyValidator())
        variablesStore = new VariablesStore()
    }

    static void exec(Scenarios scenarios) {
        yellow("\nRunning scenarios '$scenarios.name'")
        Response response = RestClient.dispatch(scenarios)

        boolean fail = validate(scenarios, response)
        store(scenarios, response)
        result.put(scenarios.name, fail)
    }

    private static boolean validate(Scenarios scenarios, Response response) {
        validators
                .stream()
                .map({ it.validate(response, scenarios) })
                .collect(toList())
                .stream()
                .any { !it }
    }

    private static void store(Scenarios scenarios, Response response) {
        variablesStore.store(response, scenarios)
    }
}

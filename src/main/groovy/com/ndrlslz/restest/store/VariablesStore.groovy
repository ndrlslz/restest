package com.ndrlslz.restest.store

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

import static com.ndrlslz.restest.core.AppContext.retrieveCurrentVariables
import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red

class VariablesStore {
    static void store(Response response, Scenarios scenarios) {
        def variables = retrieveCurrentVariables()

        scenarios.variables.forEach({ key, path ->
            def value = response.jsonPath().get(path)
            if (value) {
                variables.put(key, value)
                green("Store variable $key with value $value")
            } else {
                red("Store variable $key with value $value")
            }
        })
    }

    }

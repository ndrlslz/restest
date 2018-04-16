package com.ndrlslz.restest.handle

import com.ndrlslz.restest.client.RestClient
import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.ValidatableResponse

import static org.hamcrest.core.IsEqual.equalTo

class ScenariosExecutor {
    def exec(Scenarios scenarios) {
        println("Begin scenarios: $scenarios.name")
        ValidatableResponse response = RestClient.get(scenarios)
        try {
            response.body("data.code", equalTo("code1"))
        } catch (AssertionError ignored) {

            println("Accepted value: , but actually: ")
        }
    }
}

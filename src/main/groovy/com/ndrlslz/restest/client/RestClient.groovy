package com.ndrlslz.restest.client

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Scenarios
import io.restassured.RestAssured
import io.restassured.response.ValidatableResponse

import static io.restassured.RestAssured.basic
import static io.restassured.RestAssured.given

class RestClient {
    static void configure() {
        def api = AppContext.currentApi.get()
        println("Begin configure api: $api.name")
        RestAssured.baseURI = api.endpoint
        RestAssured.port = api.port
        if (api.username && api.password) {
            RestAssured.authentication = basic(api.username, api.password)
        }
    }

    static ValidatableResponse get(Scenarios scenarios) {
        ValidatableResponse response = given()
                .headers(scenarios.headers)
                .get(scenarios.path)
                .then()

        response
    }

    static ValidatableResponse post(Scenarios scenarios) {
        def response = given()
                .body(scenarios.body)
                .headers(scenarios.headers)
                .post(scenarios.path)
                .getBody()


        response
    }
}

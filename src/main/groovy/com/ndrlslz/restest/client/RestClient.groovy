package com.ndrlslz.restest.client

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Scenarios
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse

import static io.restassured.RestAssured.basic
import static io.restassured.RestAssured.given

class RestClient {
    static void configure() {
        def api = AppContext.currentApi.get()
        RestAssured.baseURI = api.endpoint
        RestAssured.port = api.port
        if (api.username && api.password) {
            RestAssured.authentication = basic(api.username, api.password)
        }
    }

    static Response get(Scenarios scenarios) {
        def response = given()
                .headers(scenarios.headers)
                .get(scenarios.path)
                .andReturn()

        response
    }

    static Response post(Scenarios scenarios) {
        def response = given()
                .body(scenarios.body)
                .headers(scenarios.headers)
                .post(scenarios.path)
                .andReturn()

        response
    }
}

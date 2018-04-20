package com.ndrlslz.restest.client

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Scenarios
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ResponseOptions

import static com.ndrlslz.restest.model.Method.*
import static io.restassured.RestAssured.given

class RestClient {
    static void configure() {
        def api = AppContext.currentApi.get()
        RestAssured.baseURI = api.endpoint
        RestAssured.port = api.port
        if (api.username && api.password) {
            RestAssured.authentication = RestAssured.preemptive().basic(api.username, api.password)
        }
    }

    static Response dispatch(Scenarios scenarios) {
        def specification = given()

        if (scenarios.headers) {
            specification = specification.headers(scenarios.headers)
        }

        if (scenarios.body && GET != scenarios.method) {
            specification = specification.body(scenarios.body)
        }

        switch (scenarios.method) {
            case GET:
                specification = specification.get(scenarios.path)
                break
            case POST:
                specification = specification.post(scenarios.path)
                break
            case DELETE:
                specification = specification.delete(scenarios.path)
                break
            case PATCH:
                specification = specification.patch(scenarios.path)
        }
        (specification as ResponseOptions).andReturn() as Response
    }
}

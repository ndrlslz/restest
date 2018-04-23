package com.ndrlslz.restest.client

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Scenarios
import groovy.text.SimpleTemplateEngine
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ResponseOptions

import static com.ndrlslz.restest.core.AppContext.retrieveCurrentVariables
import static com.ndrlslz.restest.model.Method.*
import static io.restassured.RestAssured.given

class RestClient {
    private static SimpleTemplateEngine engine = new SimpleTemplateEngine()

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
                .headers(scenarios.headers)
                .body(engine.createTemplate(scenarios.body).make(retrieveCurrentVariables()).toString())
        def path = engine.createTemplate(scenarios.path).make(retrieveCurrentVariables()).toString()

        switch (scenarios.method) {
            case GET:
                specification = specification.get(path)
                break
            case POST:
                specification = specification.post(path)
                break
            case DELETE:
                specification = specification.delete(path)
                break
            case PATCH:
                specification = specification.patch(path)
        }
        (specification as ResponseOptions).andReturn() as Response
    }
}

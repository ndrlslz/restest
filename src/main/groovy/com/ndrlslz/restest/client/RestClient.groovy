package com.ndrlslz.restest.client

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Scenarios
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ResponseOptions

import static com.ndrlslz.restest.model.Method.*
import static com.ndrlslz.restest.utils.TemplateUtils.render
import static io.restassured.RestAssured.given
import static java.util.stream.Collectors.toMap

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
        def headers = scenarios.headers.entrySet()
                .stream()
                .collect(toMap({ it.key.toString() }, {
            render(it.value.toString())
        }))

        def specification = given()
                .headers(headers)
                .body(render(scenarios.body))
        def path = render(scenarios.path)

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

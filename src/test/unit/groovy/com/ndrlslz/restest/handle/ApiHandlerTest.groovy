package com.ndrlslz.restest.handle

import com.ndrlslz.restest.core.AppContext
import io.restassured.RestAssured
import spock.lang.Specification

class ApiHandlerTest extends Specification {
    def "shouldHandleApiDefinitionSuccessfully"() {
        given:
        def handler = new ApiHandler()
        def yml = [api: [name    : 'test api',
                         username: 'apiuser',
                         password: 'passw0rd',
                         endpoint: 'http://localhost',
                         port    : 8080]]

        when:
        handler.handle(yml.api)

        then:
        def api = AppContext.currentApi.get()
        api.username == 'apiuser'
        api.password == 'passw0rd'
        api.endpoint == 'http://localhost'
        api.port == 8080
        RestAssured.port == api.port
        RestAssured.baseURI == api.endpoint
    }
}

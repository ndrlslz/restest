package com.ndrlslz.restest.store

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Scenarios
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import spock.lang.Specification

class VariablesStoreTest extends Specification {
    void cleanup() {
        AppContext.clear()
    }

    def "shouldStoreVariables"() {
        given:
        def response = Mock(Response)
        def jsonPath = Mock(JsonPath)
        def scenarios = new Scenarios()

        and:
        scenarios.variables = [:]
        scenarios.variables.var1 = "path1"
        response.jsonPath() >> jsonPath
        jsonPath.get("path1") >> "value1"

        when:
        VariablesStore.store(response, scenarios)

        then:
        AppContext.currentVariables.get().get("var1") == "value1"
    }
}

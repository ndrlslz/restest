package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import spock.lang.Specification

class ResponseHeaderValidatorTest extends Specification {
    private ResponseHeaderValidator validator

    void setup() {
        validator = new ResponseHeaderValidator()
    }

    def "shouldValidateSuccessfully"() {
        given:
        Scenarios scenarios = new Scenarios()
        def response = Mock(Response)
        def validatableResponse = Mock(ValidatableResponse)

        and:
        scenarios.expect.headers = [:]
        scenarios.expect.headers.data = 1
        response.then() >> validatableResponse
        validatableResponse.header("data", _) >> validatableResponse

        when:
        def success = validator.validate(response, scenarios)

        then:
        success
    }

    def "shouldValidateFail"() {
        given:
        Scenarios scenarios = new Scenarios()
        def response = Mock(Response)
        def validatableResponse = Mock(ValidatableResponse)

        and:
        scenarios.expect.headers = [:]
        scenarios.expect.headers.data = 1
        response.then() >> validatableResponse
        validatableResponse.header("data", _) >> { throw new AssertionError() }

        when:
        def success = validator.validate(response, scenarios)

        then:
        !success

    }
}

package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import spock.lang.Specification

class StatusCodeValidatorTest extends Specification {
    private Validator statusCodeValidator

    void setup() {
        statusCodeValidator = new StatusCodeValidator()
    }

    def "shouldValidateSuccessfully"() {
        given:
        def scenarios = new Scenarios()
        def response = Mock(Response)
        def validatableResponse = Mock(ValidatableResponse)

        and:
        scenarios.expect.statusCode = 200
        response.then() >> validatableResponse
        validatableResponse.statusCode(200) >> validatableResponse

        when:
        def success = statusCodeValidator.validate(response, scenarios)

        then:
        success
    }

    def "shouldValidateFail"() {
        given:
        def scenarios = new Scenarios()
        def response = Mock(Response)
        def validatableResponse = Mock(ValidatableResponse)

        and:
        scenarios.expect.statusCode = 200
        response.then() >> validatableResponse
        validatableResponse.statusCode(200) >> { throw new AssertionError() }

        when:
        def success = statusCodeValidator.validate(response, scenarios)

        then:
        !success

    }
}

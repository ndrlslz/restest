package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import spock.lang.Specification

import static org.hamcrest.core.IsEqual.equalTo

class ResponseBodyValidatorTest extends Specification {
    private Validator responseBodyValidator

    void setup() {
        responseBodyValidator = new ResponseBodyValidator()
    }

    def "shouldValidateSuccessfully"() {
        given:
        def response = Mock(Response)
        def validatableResponse = Mock(ValidatableResponse)
        def scenarios = new Scenarios()

        and:
        scenarios.responseBody = new HashMap<>()
        scenarios.responseBody.data = 1
        response.then() >> validatableResponse
        validatableResponse.body("data", equalTo(1)) >> validatableResponse

        when:
        def success = responseBodyValidator.validate(response, scenarios)

        then:
        success
    }

    def "shouldValidateFail"() {
        given:
        def response = Stub(Response)
        def validatableResponse = Stub(ValidatableResponse)
        def scenarios = new Scenarios()

        and:
        scenarios.responseBody = new HashMap<>()
        scenarios.responseBody.data = 1
        response.then() >> validatableResponse
        validatableResponse.body("data", _) >> { throw new RuntimeException() }

        when:
        def success = responseBodyValidator.validate(response, scenarios)

        then:
        !success
    }
}

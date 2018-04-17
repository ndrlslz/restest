package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Scenarios
import io.restassured.response.Response

interface Validator {
    boolean validate(Response response, Scenarios scenarios)
}
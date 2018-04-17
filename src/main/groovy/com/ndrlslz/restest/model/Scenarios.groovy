package com.ndrlslz.restest.model

class Scenarios {
    String name
    String path
    Method method
    String body
    Map<String, Object> headers
    Integer statusCode
    Map<String, Object> responseBody
    Map<String, Object> variables
}

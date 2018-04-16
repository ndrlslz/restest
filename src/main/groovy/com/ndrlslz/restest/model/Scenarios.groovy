package com.ndrlslz.restest.model

class Scenarios {
    String name
    String path
    Method method
    String body
    Map<String, String> headers
    int statusCode
    Map<String, String> responseBody
    Map<String, String> variables
}

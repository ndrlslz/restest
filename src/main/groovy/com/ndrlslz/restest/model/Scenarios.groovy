package com.ndrlslz.restest.model

class Scenarios {
    String name
    String path
    String method
    String body
    Map<String, String> headers
    Map<String, String> variables
    Expectation expect = new Expectation()
}

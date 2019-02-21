package com.ndrlslz.restest.function

import io.restassured.response.Response

import static java.lang.String.format

class FunctionExecutor {
    private static List<Function> functions = new ArrayList<>()

    static {
        functions.add(new Contains())
        functions.add(new ContainsString())
        functions.add(new HasItems())
        functions.add(new GreaterThan())
        functions.add(new LessThan())
        functions.add(new StartsWith())
        functions.add(new EndsWith())
        functions.add(new Is())
    }

    static void execute(String function, String parameters, Response response, String path) {
        functions
                .stream()
                .filter({ it.match(function) })
                .findFirst()
                .orElseThrow({ new IllegalArgumentException(format("function %s is not supported yet", function)) })
                .execute(response, path, parameters)
    }
}

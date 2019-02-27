package com.ndrlslz.restest.function

import io.restassured.response.Response

import static java.lang.String.format

class FunctionExecutor {
    private static List<Function> functions = new ArrayList<>()

    static {
        FunctionLoader
                .loadClasses()
                .forEach({ clazz -> functions.add(clazz.newInstance())
        })
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
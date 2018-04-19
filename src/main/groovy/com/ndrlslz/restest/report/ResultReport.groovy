package com.ndrlslz.restest.report

import java.util.concurrent.ConcurrentHashMap

import static com.ndrlslz.restest.utils.Printer.white
import static java.util.stream.Collectors.toMap

class ResultReport {
    static ConcurrentHashMap<String, Boolean> result = new ConcurrentHashMap<>()

    static void printResult() {
        def totalCount = result.size()
        def failedScenarios = result
                .entrySet()
                .stream()
                .filter { entry -> entry.getValue() }
                .collect(toMap({ it.key }, { it.value }))

        def failedCount = failedScenarios.size()
        def successCount = totalCount - failedCount

        white("\n======================================================")
        white("Totally run scenarios count: $totalCount")
        white("Success scenarios count:     $successCount")
        white("Failure scenarios count:     $failedCount")
        if (failedCount > 0) {
            white("\nFailure scenarios:")
            failedScenarios.each { name, _ ->
                white("# $name")
            }
        }
        white("======================================================")
    }
}

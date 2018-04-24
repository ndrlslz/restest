package com.ndrlslz.restest.app

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.ndrlslz.restest.App
import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.report.ResultReport
import org.junit.Rule
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import static java.util.stream.Collectors.toMap

class AppTest extends Specification {
    @Rule
    WireMockRule wireMockRule = new WireMockRule(options().port(8080).usingFilesUnderClasspath("stub"))

    void setup() {
        ResultReport.clear()
        AppContext.clear()
    }

    def "appGetTest"() {
        when:
        App.main("src/test/intg/resources/appGetTest.yml")
        println("***********************")
        then:
        ResultReport.result.size() == 2
        allScenariosSucceed()
    }

    def "appGetWithAuthTest"() {
        when:
        App.main("src/test/intg/resources/appGetWithAuthTest.yml")

        then:
        ResultReport.result.size() == 1
        allScenariosSucceed()
    }

    def "appPostTest"() {
        when:
        App.main("src/test/intg/resources/appPostTest.yml")

        then:
        ResultReport.result.size() == 1
        allScenariosSucceed()
    }

    def "appVariableTest"() {
        when:
        App.main("src/test/intg/resources/appTestWithVariable.yml")


        then:
        ResultReport.result.size() == 2
        allScenariosSucceed()
    }

    private static boolean allScenariosSucceed() {
        ResultReport.result
                .entrySet()
                .stream()
                .filter { entry -> entry.getValue() }
                .collect(toMap({ it.key }, { it.value }))
                .size() == 0
    }

}

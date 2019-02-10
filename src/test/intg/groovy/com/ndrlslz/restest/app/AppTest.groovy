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

    def "appBasicTest"() {
        when:
        App.main("src/test/intg/resources/ApiDefinition.yml")
        then:
        allScenariosSucceed()
    }

    def "basicAuthTest"() {
        when:
        App.main("src/test/intg/resources/ApiDefinitionWithBasicAuth.yml")

        then:
        allScenariosSucceed()
    }

    def "functionTest"() {
        when:
        App.main("src/test/intg/resources/ApiDefinitionWithFunction.yml")
        then:
        allScenariosSucceed()
    }

    def "specialCharacterTest"() {
        when:
        App.main("src/test/intg/resources/ApiDefinitionWithSpecialChar.yml")
        then:
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
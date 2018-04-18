package com.ndrlslz.restest.app

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.ndrlslz.restest.App
import org.junit.Rule
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

class AppTest extends Specification {
    @Rule
    WireMockRule wireMockRule = new WireMockRule(options().port(8080).usingFilesUnderClasspath("stub"))

    def "appTest"() {
        when:
        App.main("src/test/intg/resources/test.yml")

        then:
        true
    }
}

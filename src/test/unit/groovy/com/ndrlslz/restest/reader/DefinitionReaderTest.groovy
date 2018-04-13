package com.ndrlslz.restest.reader

import spock.lang.Specification

import java.nio.file.NoSuchFileException

class DefinitionReaderTest extends Specification {

    public static final String YML_LOCATION_FOLDER = "src/test/unit/resources"

    def "shouldReadDefinitionFile"() {
        when:
        def ymls = DefinitionReader.read("${YML_LOCATION_FOLDER}/test.yml")

        then:
        ymls[0].api.name as String == "test api"
        ymls[0].scenarios.get(0).name == "call api endpoint"
    }

    def "shouldReadDefinitionFolder"() {
        when:
        def ymls = DefinitionReader.read(YML_LOCATION_FOLDER)

        then:
        ymls[0].api.name as String == "test api"
        ymls[0].scenarios.get(0).name == "call api endpoint"
    }

    def "shouldRaiseException"() {
        when:
        DefinitionReader.read("test.yml")

        then:
        thrown(NoSuchFileException)
    }
}

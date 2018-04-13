package com.ndrlslz.restest.reader

import org.yaml.snakeyaml.Yaml

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class DefinitionReader {
    static Yaml parser = new Yaml()
    static final String YML_EXTENSION = ".yml"

    private static readFrom(Path path) {
        parser.load(Files.newBufferedReader(path))
    }

    static read(String input) {
        List ymls = new ArrayList<>()
        def path = Paths.get(input)

        if (Files.isDirectory(path)) {
            Files
                    .walk(path)
                    .filter(Files.&isRegularFile)
                    .filter({ it.toString().endsWith(YML_EXTENSION) })
                    .forEach({ ymls.add(readFrom(it)) })
        } else {
            ymls.add(readFrom(path))
        }
        ymls
    }
}

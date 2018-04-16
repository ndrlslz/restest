package com.ndrlslz.restest

import com.ndrlslz.restest.core.AppExecutor
import com.ndrlslz.restest.reader.DefinitionReader

class App {

    static void main(String[] args) {
        AppExecutor appExecutor = new AppExecutor()

        List<Map> ymls = DefinitionReader.read("src/main/resources/test.yml") as List
        ymls.stream().forEach({ appExecutor.exec(it) })
    }
}

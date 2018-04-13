package com.ndrlslz.restest

import com.ndrlslz.restest.core.AppExecutor
import com.ndrlslz.restest.reader.DefinitionReader

import java.util.function.Consumer

class App {

    static void main(String[] args) {
        AppExecutor appExecutor = new AppExecutor()

        List<Map> ymls= DefinitionReader.read("src/main/resources/test.yml") as List
        ymls.stream().forEach(new Consumer<Map>() {
            @Override
            void accept(Map map) {
                appExecutor.exec(map)
            }
        })
    }
}

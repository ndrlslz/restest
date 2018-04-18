package com.ndrlslz.restest

import com.ndrlslz.restest.core.AppExecutor
import com.ndrlslz.restest.reader.DefinitionReader

class App {
    static void main(String[] args) {
        String path = args[0]
        AppExecutor appExecutor = new AppExecutor()

        List<Map> ymls = DefinitionReader.read(path) as List
        ymls.stream().forEach({ appExecutor.exec(it) })
    }
}

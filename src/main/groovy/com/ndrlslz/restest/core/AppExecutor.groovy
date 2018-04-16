package com.ndrlslz.restest.core

import com.ndrlslz.restest.handle.ApiHandler
import com.ndrlslz.restest.handle.ScenariosHandler

class AppExecutor {
    ApiHandler apiHandler = new ApiHandler()
    ScenariosHandler scenariosHandler = new ScenariosHandler()

    def exec(Map yml) {
        apiHandler.handle(yml.api as Map)
        scenariosHandler.handle(yml.scenarios as Map)
    }
}

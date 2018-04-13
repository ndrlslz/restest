package com.ndrlslz.restest.core

import com.ndrlslz.restest.handle.ApiHandler

class AppExecutor {
    ApiHandler apiHandler = new ApiHandler()

    def exec(Map yml) {
        apiHandler.handle(yml.api as Map)

        println(AppContext.currentApi.get().name)
    }
}

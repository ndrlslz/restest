package com.ndrlslz.restest.handle

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Api

import static com.ndrlslz.restest.utils.Utils.notNull

class ApiHandler implements Handler {
    @Override
    def handle(Map yml) {
        def currentApi = new Api()
        def api = notNull(yml, "api")
        currentApi.name = notNull(api.name, "api.name")
        currentApi.endpoint = notNull(api.endpoint, "api.endpoint")
        currentApi.username = api.username
        currentApi.password = api.password

        AppContext.currentApi.set(currentApi)
    }
}
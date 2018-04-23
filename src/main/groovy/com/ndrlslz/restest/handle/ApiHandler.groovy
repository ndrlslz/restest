package com.ndrlslz.restest.handle

import com.ndrlslz.restest.client.RestClient
import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Api

import static com.ndrlslz.restest.utils.Printer.cyan
import static com.ndrlslz.restest.utils.Utils.notNull

class ApiHandler implements Handler {
    @Override
    def handle(Map yml) {
        def currentApi = new Api()
        def api = notNull(yml, "api")
        currentApi.name = notNull(api.name, "api.name")
        currentApi.endpoint = notNull(api.endpoint, "api.endpoint")
        currentApi.port = notNull(api.port, "api.port") as int
        currentApi.username = api.username
        currentApi.password = api.password

        AppContext.currentApi.set(currentApi)
        RestClient.configure()
        cyan("Running REST API Test")
        cyan("Name: $api.name")
        cyan("Endpoint: $api.endpoint")
        cyan("Port: $api.port")
    }
}
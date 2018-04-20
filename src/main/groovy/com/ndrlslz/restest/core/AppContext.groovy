package com.ndrlslz.restest.core

import com.ndrlslz.restest.model.Api
import com.ndrlslz.restest.model.Scenarios

class AppContext {
    static ThreadLocal<Api> currentApi = new ThreadLocal<>()
    static ThreadLocal<Scenarios> currentScenarios = new ThreadLocal<>()
    static ThreadLocal<Set> variables = new ThreadLocal<>()

    static void clear() {
        currentApi.remove()
        currentScenarios.remove()
        variables.remove()
    }
}

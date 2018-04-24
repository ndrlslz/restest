package com.ndrlslz.restest.core

import com.ndrlslz.restest.model.Api
import com.ndrlslz.restest.model.Scenarios

import static java.util.Objects.isNull

class AppContext {
    static ThreadLocal<Api> currentApi = new ThreadLocal<>()
    static ThreadLocal<Scenarios> currentScenarios = new ThreadLocal<>()
    static ThreadLocal<HashMap> currentVariables = new ThreadLocal<>()

    static void clear() {
        currentApi.remove()
        currentScenarios.remove()
        currentVariables.remove()
    }

    static Map retrieveCurrentVariables() {
        if (isNull(currentVariables.get())) {
            currentVariables.set(new HashMap())
        }
        currentVariables.get()
    }

}

package com.ndrlslz.restest.handle

import com.ndrlslz.restest.core.AppContext
import com.ndrlslz.restest.model.Scenarios

import static com.ndrlslz.restest.utils.Utils.notNull

class ScenariosHandler implements Handler {
    private ScenariosExecutor scenariosExecutor = new ScenariosExecutor()

    @Override
    def handle(Map yml) {
        List<Map> scenarios = notNull(yml, "scenarios") as List

        scenarios.stream()
                .map(this.&assemble)
                .forEach(scenariosExecutor.&exec)
    }

    private static Scenarios assemble(Map scenarios) {
        Scenarios currentScenarios = new Scenarios()
        currentScenarios.name = notNull(scenarios.name, "scenarios.name")
        currentScenarios.path = notNull(scenarios.path, "scenarios.path")
        currentScenarios.method = notNull(scenarios.method, "scenarios.method")
        currentScenarios.body = scenarios.body ?: ""
        currentScenarios.headers = scenarios.headers ?: [:]
        currentScenarios.expect.body = scenarios.expect.body ?: [:]
        currentScenarios.expect.headers = scenarios.expect.headers ?: [:]
        currentScenarios.expect.statusCode = scenarios.expect.status ?: 200
        currentScenarios.variables = scenarios.variables ?: [:]


        AppContext.currentScenarios.set(currentScenarios)
        currentScenarios
    }
}

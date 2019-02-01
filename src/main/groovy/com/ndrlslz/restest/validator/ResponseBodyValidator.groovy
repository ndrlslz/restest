package com.ndrlslz.restest.validator

import com.ndrlslz.restest.function.FunctionExecutor
import com.ndrlslz.restest.model.Expression
import com.ndrlslz.restest.model.Scenarios
import com.ndrlslz.restest.utils.ExpressionParser
import io.restassured.response.Response

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red
import static com.ndrlslz.restest.utils.TemplateUtils.render

class ResponseBodyValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        scenarios.expect.body.each { expectedPath, expectedExpression ->
            Expression expression = ExpressionParser.parse(expectedExpression)

            String operation = expression.function
            String path = render(expectedPath)
            String value = render(expression.parameters)

            try {
                FunctionExecutor.execute(operation, value, response, path)
                green("Expect body $path $operation $value")
            } catch (AssertionError ignored) {
                red("Expect body $path $operation $value, but actually is ${response.jsonPath().get(path)}")
                success = false
            } catch (Exception exception) {
                red("Expect body $path $operation $value, but exception is ${exception.class}: ${exception.message}")
                success = false
            }
        }
        success
    }
}
package com.ndrlslz.restest.validator

import com.ndrlslz.restest.model.Expression
import com.ndrlslz.restest.model.Scenarios
import com.ndrlslz.restest.utils.ExpressionParser
import io.restassured.response.Response

import java.util.function.Function

import static com.ndrlslz.restest.utils.Printer.green
import static com.ndrlslz.restest.utils.Printer.red
import static com.ndrlslz.restest.utils.TemplateUtils.render
import static org.hamcrest.Matchers.*

//TODO support assert function like contains(), greaterThan().....
class ResponseBodyValidator implements Validator {
    @Override
    boolean validate(Response response, Scenarios scenarios) {
        boolean success = true
        scenarios.expect.body.each { expectedPath, expectedExpression ->
            Expression expression = ExpressionParser.parse(expectedExpression)

            String operation = expression.function
            String content = expression.parameters

            String path = render(expectedPath)
            String value = render(content)

            try {
                if (operation == "is") {
                    response.then().body(path, hasToString(equalTo(value)))
                } else if (operation == "greaterThan") {
                    response.then().body(path, greaterThan(Integer.valueOf(value)))
                } else if (operation == "contains") {
                    String[] parameters = value.split(",")

                    try {
                        Integer[] result = Arrays.asList(parameters).stream().map(new Function<String, Integer>() {
                            @Override
                            Integer apply(String parameter) {
                                return Integer.valueOf(parameter.trim())
                            }
                        }).toArray()

                        response.then().body(path, containsInAnyOrder(result))
                    } catch (Exception ignored) {
                        String[] stringResult = Arrays.asList(parameters).stream().map(new Function<String, String>() {
                            @Override
                            String apply(String s) {
                                return s.trim()
                            }
                        }).toArray()

                        response.then().body(path, containsInAnyOrder(stringResult))
                    }
                }
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
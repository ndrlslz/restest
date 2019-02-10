package com.ndrlslz.restest.utils

import com.ndrlslz.restest.model.Expression

import java.util.regex.Matcher
import java.util.regex.Pattern

import static com.ndrlslz.restest.function.Is.FUNCTION_IS
import static java.util.Objects.toString

class ExpressionParser {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("^(.*?)\\((.*)\\)")

    static Expression parse(Object expression) {
        String function
        String parameters

        Matcher matcher = EXPRESSION_PATTERN.matcher(toString(expression))
        if (matcher.matches()) {
            function = matcher.group(1)
            parameters = matcher.group(2)
        } else {
            function = FUNCTION_IS
            parameters = toString(expression)
        }

        new Expression(function, parameters)
    }
}

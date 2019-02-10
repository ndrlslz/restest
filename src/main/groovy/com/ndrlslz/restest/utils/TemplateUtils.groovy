package com.ndrlslz.restest.utils

import groovy.text.GStringTemplateEngine
import groovy.text.TemplateEngine

import static com.ndrlslz.restest.core.AppContext.retrieveCurrentVariables

class TemplateUtils {
    static final TemplateEngine templateEngine = new GStringTemplateEngine()

    static String render(Object template) {
        templateEngine.createTemplate(Objects.toString(template)).make(retrieveCurrentVariables()).toString()
    }
}

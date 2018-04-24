package com.ndrlslz.restest.utils

import groovy.text.SimpleTemplateEngine

import static com.ndrlslz.restest.core.AppContext.retrieveCurrentVariables

class TemplateUtils {
    static final SimpleTemplateEngine templateEngine = new SimpleTemplateEngine()

    static String render(Object template) {
        templateEngine.createTemplate(Objects.toString(template)).make(retrieveCurrentVariables()).toString()
    }
}

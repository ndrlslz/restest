package com.ndrlslz.restest.function

import org.reflections.Reflections

class FunctionLoader {
    public static final String FUNCTION_PACKAGE = "com.ndrlslz.restest.function"

    static Set<Class<? extends Function>> loadClasses() {
        return new Reflections(FUNCTION_PACKAGE).getSubTypesOf(Function.class)
    }
}
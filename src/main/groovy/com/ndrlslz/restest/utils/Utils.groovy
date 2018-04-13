package com.ndrlslz.restest.utils

class Utils {
    static def notNull(Object o, String propertyName) {
        if (o == null) {
            throw new MissingPropertyException("$propertyName can't be null")
        }
        o
    }
}

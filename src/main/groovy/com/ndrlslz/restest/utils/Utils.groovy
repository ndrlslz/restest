package com.ndrlslz.restest.utils

class Utils {
    static def notNull(Object o, String propertyName) {
        if (o == null) {
            throw new MissingPropertyException("Yml definition format error, $propertyName can't be null")
        }
        o
    }
}

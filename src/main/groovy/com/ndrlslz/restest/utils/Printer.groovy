package com.ndrlslz.restest.utils

class Printer {
    static void red(String text) {
        println("\u001B[31m$text")
    }

    static void green(String text) {
        println("\u001B[32m$text")
    }

    static void yellow(String text) {
        println("\u001B[33m$text")
    }

    static void white(String text) {
        println("\u001B[0m$text")
    }

    static void cyan(String text) {
        println("\u001B[36m$text")
    }
}

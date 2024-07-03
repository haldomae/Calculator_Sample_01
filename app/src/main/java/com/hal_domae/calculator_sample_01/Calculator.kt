package com.hal_domae.calculator_sample_01


class Calculator {
    fun calcForEquation(equation: String): Double{
        val tokens = equation.split("(?<=[-+×÷])|(?=[-+×÷])".toRegex())
        val stack = mutableListOf<Double>()

        var i = 0
        while (i < tokens.size) {
            when (tokens[i]) {
                "+" -> {
                    val a = stack.removeAt(stack.size - 1)
                    val b = tokens[++i].toDouble()
                    stack.add(a + b)
                }
                "-" -> {
                    val a = stack.removeAt(stack.size - 1)
                    val b = tokens[++i].toDouble()
                    stack.add(a - b)
                }
                "×" -> {
                    val a = stack.removeAt(stack.size - 1)
                    val b = tokens[++i].toDouble()
                    stack.add(a * b)
                }
                "÷" -> {
                    val a = stack.removeAt(stack.size - 1)
                    val b = tokens[++i].toDouble()
                    stack.add(a / b)
                }
                else -> stack.add(tokens[i].toDouble())
            }
            i++
        }
        return stack[0]
    }
}
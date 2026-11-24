package ru.abrakov.main;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

/** Вычисляет X в степени Y; строки преобразуются через parseInt, степень — через pow. */
public class PowerCalculator {
    private String lastBaseText;
    private String lastExponentText;
    private Double lastResult;

    public PowerCalculator() {
        lastBaseText = "не вычислялось";
        lastExponentText = "не вычислялось";
        lastResult = null;
    }

    public double calculate(String x, String y) {
        if (x == null || x.isBlank() || y == null || y.isBlank()) {
            throw new IllegalArgumentException("Обе строки X и Y должны содержать целые числа");
        }

        int base = parseInt(x.trim());
        int exponent = parseInt(y.trim());
        double result = pow(base, exponent);

        lastBaseText = x.trim();
        lastExponentText = y.trim();
        lastResult = result;
        return result;
    }

    @Override
    public String toString() {
        if (lastResult == null) {
            return "Калькулятор степени: вычисления еще не выполнялись";
        }
        return "Последнее вычисление: " + lastBaseText + " ^ " + lastExponentText + " = " + lastResult;
    }
}

package ru.abrakov.main;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class PowerCalculator {
    private final String name;

    public PowerCalculator() {
        this.name = "Возведение в степень";
    }

    public double calculate(String x, String y) {
        if (x == null || x.isBlank() || y == null || y.isBlank()) {
            throw new IllegalArgumentException("Необходимо указать оба числа.");
        }
        int base = parseInt(x.trim());
        int exponent = parseInt(y.trim());
        if (base == 0 && exponent < 0) {
            throw new IllegalArgumentException("Ноль нельзя возводить в отрицательную степень.");
        }
        double result = pow(base, exponent);
        if (!Double.isFinite(result)) {
            throw new IllegalArgumentException("Результат выходит за пределы вещественного типа.");
        }
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}

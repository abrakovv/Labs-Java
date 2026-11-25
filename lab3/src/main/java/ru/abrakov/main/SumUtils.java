package ru.abrakov.main;

public class SumUtils {
    private final String name;

    public SumUtils() {
        this.name = "Сложение чисел";
    }

    public double sum(double... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Для сложения нужно передать хотя бы одно число.");
        }

        double result = 0.0;
        for (double number : numbers) {
            if (!Double.isFinite(number)) {
                throw new IllegalArgumentException("Все числа должны быть конечными.");
            }
            result += number;
            if (!Double.isFinite(result)) {
                throw new IllegalArgumentException("Сумма выходит за допустимый диапазон.");
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}

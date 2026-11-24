package ru.abrakov.main;

/** Сервис для сложения произвольного набора вещественных чисел. */
public class SumUtils {
    private final String operationName;

    public SumUtils() {
        this.operationName = "Сложение вещественных чисел";
    }

    public double sum(double... numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Набор чисел не должен быть null");
        }

        double result = 0.0;
        for (int i = 0; i < numbers.length; i++) {
            if (!Double.isFinite(numbers[i])) {
                throw new IllegalArgumentException("Элемент с индексом " + i + " не является конечным числом");
            }
            result += numbers[i];
        }
        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Результат сложения выходит за допустимый диапазон double");
        }
        return result;
    }

    @Override
    public String toString() {
        return "Сервис: " + operationName;
    }
}

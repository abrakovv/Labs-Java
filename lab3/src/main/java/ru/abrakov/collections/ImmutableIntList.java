package ru.abrakov.collections;

import java.util.Arrays;

/**
 * Обертка над массивом целых чисел.
 * Внутренний массив скрыт: входные и возвращаемые массивы копируются.
 * Замена элемента доступна только через проверяемый метод set().
 */
public class ImmutableIntList {
    private final int[] values;

    /** Принимает ноль или более чисел; массив копируется. */
    public ImmutableIntList(int... values) {
        if (values == null) {
            throw new IllegalArgumentException("Массив значений не должен быть null");
        }
        this.values = Arrays.copyOf(values, values.length);
    }

    /** Создает независимую копию другого списка. */
    public ImmutableIntList(ImmutableIntList other) {
        if (other == null) {
            throw new IllegalArgumentException("Исходный список не должен быть null");
        }
        this.values = Arrays.copyOf(other.values, other.values.length);
    }

    public int get(int index) {
        checkIndex(index);
        return values[index];
    }

    /** Заменяет элемент внутри обертки после проверки допустимости индекса. */
    public void set(int index, int newValue) {
        checkIndex(index);
        values[index] = newValue;
    }

    public boolean isEmpty() {
        return values.length == 0;
    }

    public int size() {
        return values.length;
    }

    /** Возвращает копию внутреннего массива. */
    public int[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException(
                    "Индекс " + index + " вне допустимого диапазона [0, " + (values.length - 1) + "]"
            );
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}

package ru.abrakov.collections;

import java.util.Arrays;

public class ImmutableIntList {
    private final int[] values;

    public ImmutableIntList(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Массив не должен быть null.");
        }
        this.values = values.clone();
    }

    public ImmutableIntList(int firstValue, int... otherValues) {
        if (otherValues == null) {
            throw new IllegalArgumentException("Набор значений не должен быть null.");
        }
        this.values = new int[otherValues.length + 1];
        this.values[0] = firstValue;
        System.arraycopy(otherValues, 0, this.values, 1, otherValues.length);
    }

    public ImmutableIntList(ImmutableIntList other) {
        if (other == null) {
            throw new IllegalArgumentException("Список для копирования не должен быть null.");
        }
        this.values = other.values.clone();
    }

    public int get(int index) {
        checkIndex(index);
        return values[index];
    }

    public void set(int index, int value) {
        checkIndex(index);
        values[index] = value;
    }

    public boolean isEmpty() {
        return values.length == 0;
    }

    public int size() {
        return values.length;
    }

    public int[] toArray() {
        return values.clone();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException(
                    "Индекс должен быть от 0 до " + (values.length - 1) + ". Получено: " + index
            );
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}

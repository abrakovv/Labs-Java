package ru.abrakov.points;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractPoint {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private final int[] coordinates;

    protected AbstractPoint(int... coordinates) {
        if (coordinates == null || coordinates.length < 1 || coordinates.length > 3) {
            throw new IllegalArgumentException("Точка должна иметь одну, две или три координаты.");
        }
        this.coordinates = coordinates.clone();
    }

    public int[] getCoordinates() {
        return coordinates.clone();
    }

    protected String coordinatesToString() {
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < coordinates.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(coordinates[i]);
        }
        return result.append('}').toString();
    }

    protected static String validateColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Цвет не должен быть пустым.");
        }
        return color.trim();
    }

    protected static LocalTime parseTime(String value) {
        if (!isValidTime(value)) {
            throw new IllegalArgumentException("Время должно быть указано в формате ЧЧ:ММ от 00:00 до 23:59.");
        }
        return LocalTime.parse(value.trim(), TIME_FORMAT);
    }

    public static boolean isValidTime(String value) {
        if (value == null || !value.matches("(?:[01]\\d|2[0-3]):[0-5]\\d")) {
            return false;
        }
        try {
            LocalTime.parse(value, TIME_FORMAT);
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }

    protected static String formatTime(LocalTime time) {
        return time.format(TIME_FORMAT);
    }

    @Override
    public abstract String toString();
}

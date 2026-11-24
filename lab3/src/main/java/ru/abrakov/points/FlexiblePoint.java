package ru.abrakov.points;

/**
 * Точка с одной, двумя или тремя координатами и необязательными
 * характеристиками: цветом и временем появления.
 */
public class FlexiblePoint {
    private final Integer x;
    private final Integer y;
    private final Integer z;
    private final String color;
    private final String appearanceTime;

    public FlexiblePoint(Integer x, Integer y, Integer z, String color, String appearanceTime) {
        if (x == null) {
            throw new IllegalArgumentException("Необходимо задать хотя бы координату X");
        }
        if (z != null && y == null) {
            throw new IllegalArgumentException("Для координаты Z необходимо задать координату Y");
        }
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = normalizeOptionalText(color);
        this.appearanceTime = normalizeOptionalText(appearanceTime);
    }

    public FlexiblePoint(int x) {
        this(x, null, null, null, null);
    }

    public FlexiblePoint(int x, String color) {
        this(x, null, null, color, null);
    }

    public FlexiblePoint(int x, int y) {
        this(x, y, null, null, null);
    }

    public FlexiblePoint(int x, int y, String color, String appearanceTime) {
        this(x, y, null, color, appearanceTime);
    }

    public FlexiblePoint(int x, int y, int z) {
        this(x, y, z, null, null);
    }

    public FlexiblePoint(int x, int y, int z, String color, String appearanceTime) {
        this(Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), color, appearanceTime);
    }

    private static String normalizeOptionalText(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public String getColor() {
        return color;
    }

    public String getAppearanceTime() {
        return appearanceTime;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Точка {").append(x);
        if (y != null) {
            result.append(", ").append(y);
        }
        if (z != null) {
            result.append(", ").append(z);
        }
        result.append('}');

        if (color != null) {
            result.append(", цвет: ").append(color);
        }
        if (appearanceTime != null) {
            result.append(", время появления: ").append(appearanceTime);
        }
        return result.toString();
    }
}

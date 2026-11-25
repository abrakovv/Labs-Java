package ru.abrakov.points;

public class ColoredPoint extends AbstractPoint {
    private final String color;

    public ColoredPoint(int x, String color) {
        this(new int[]{x}, color);
    }

    public ColoredPoint(int[] coordinates, String color) {
        super(coordinates);
        this.color = validateColor(color);
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Точка " + coordinatesToString() + ", цвет: " + color;
    }
}

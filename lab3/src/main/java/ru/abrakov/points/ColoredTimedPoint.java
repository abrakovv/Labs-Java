package ru.abrakov.points;

import java.time.LocalTime;

public class ColoredTimedPoint extends AbstractPoint {
    private final String color;
    private final LocalTime appearanceTime;

    public ColoredTimedPoint(int[] coordinates, String time, String color) {
        super(coordinates);
        this.appearanceTime = parseTime(time);
        this.color = validateColor(color);
    }

    public String getColor() {
        return color;
    }

    public LocalTime getAppearanceTime() {
        return appearanceTime;
    }

    @Override
    public String toString() {
        return "Точка " + coordinatesToString()
                + ", цвет: " + color
                + ", время появления: " + formatTime(appearanceTime);
    }
}

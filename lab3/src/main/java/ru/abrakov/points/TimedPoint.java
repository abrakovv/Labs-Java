package ru.abrakov.points;

import java.time.LocalTime;

public class TimedPoint extends AbstractPoint {
    private final LocalTime appearanceTime;

    public TimedPoint(int[] coordinates, String time) {
        super(coordinates);
        this.appearanceTime = parseTime(time);
    }

    public LocalTime getAppearanceTime() {
        return appearanceTime;
    }

    @Override
    public String toString() {
        return "Точка " + coordinatesToString() + ", время появления: " + formatTime(appearanceTime);
    }
}

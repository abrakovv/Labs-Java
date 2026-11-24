package ru.abrakov.geometry;

import java.util.Objects;

/**
 * Двумерная точка на координатной плоскости.
 * Координаты доступны для чтения и изменения через методы класса.
 */
public class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Возвращает евклидово расстояние до другой двумерной точки. */
    public double distanceTo(Point2D other) {
        Objects.requireNonNull(other, "Вторая точка не должна быть null");
        return Math.hypot((double) x - other.x, (double) y - other.y);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        // Точки разных размерностей не считаются равными.
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Point2D point = (Point2D) object;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + "; " + y + "}";
    }
}

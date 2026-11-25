package ru.abrakov.geometry;

public class Point2D {
    private int x;
    private int y;

    public Point2D() {
        this(0, 0);
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D other) {
        this(requirePoint(other).x, other.y);
    }

    private static Point2D requirePoint(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException("Точка не должна быть null.");
        }
        return point;
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

    public double distanceTo(Point2D other) {
        requirePoint(other);
        return Math.hypot((double) other.x - x, (double) other.y - y);
    }

    public Point2D copy() {
        return new Point2D(this);
    }

    @Override
    public String toString() {
        return "{" + x + "; " + y + "}";
    }
}

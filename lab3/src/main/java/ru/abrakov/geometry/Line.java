package ru.abrakov.geometry;

import java.util.Objects;

public class Line implements Cloneable {
    private Point2D start;
    private Point2D end;

    public Line(int startX, int startY, int endX, int endY) {
        this(new Point2D(startX, startY), new Point2D(endX, endY));
    }

    public Line(Point2D start, Point2D end) {
        this.start = requirePoint(start).copy();
        this.end = requirePoint(end).copy();
    }

    public Line(Line other) {
        this(requireLine(other).start, other.end);
    }

    private static Line requireLine(Line line) {
        if (line == null) {
            throw new IllegalArgumentException("Линия не должна быть null.");
        }
        return line;
    }

    private static Point2D requirePoint(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException("Координаты точки не должны быть null.");
        }
        return point;
    }

    public Point2D getStart() {
        return start.copy();
    }

    public Point2D getEnd() {
        return end.copy();
    }

    public void setStart(Point2D point) {
        this.start = requirePoint(point).copy();
    }

    public void setEnd(Point2D point) {
        this.end = requirePoint(point).copy();
    }

    public double length() {
        return start.distanceTo(end);
    }

    @Override
    public Line clone() {
        return new Line(start, end);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Line other)) {
            return false;
        }
        return start.getX() == other.start.getX()
                && start.getY() == other.start.getY()
                && end.getX() == other.end.getX()
                && end.getY() == other.end.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }
}

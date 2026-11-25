package ru.abrakov.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class PolygonalChain {
    private final List<Point2D> points;

    public PolygonalChain(Point2D... points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("Ломаная должна содержать не менее двух точек.");
        }

        this.points = new ArrayList<>(points.length);
        for (Point2D point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Точки ломаной не должны быть null.");
            }
            this.points.add(point.copy());
        }
    }

    public int getPointCount() {
        return points.size();
    }

    public Point2D getPoint(int index) {
        checkIndex(index);
        return points.get(index).copy();
    }

    public void setPoint(int index, Point2D point) {
        checkIndex(index);
        if (point == null) {
            throw new IllegalArgumentException("Точка не должна быть null.");
        }
        points.set(index, point.copy());
    }

    public double length() {
        double result = 0.0;
        for (int i = 1; i < points.size(); i++) {
            result += points.get(i - 1).distanceTo(points.get(i));
        }
        return result;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= points.size()) {
            throw new IndexOutOfBoundsException("Нет точки с индексом " + index + ".");
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Ломаная: [", "]");
        for (Point2D point : points) {
            joiner.add(point.toString());
        }
        return joiner.toString();
    }
}

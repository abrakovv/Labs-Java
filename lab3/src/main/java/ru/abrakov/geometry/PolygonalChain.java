package ru.abrakov.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/** Ломаная линия, заданная упорядоченным списком точек. */
public class PolygonalChain {
    private final List<Point2D> points;

    public PolygonalChain(List<Point2D> points) {
        Objects.requireNonNull(points, "Список точек не должен быть null");
        if (points.size() < 2) {
            throw new IllegalArgumentException("У ломаной должно быть не менее двух точек");
        }

        this.points = new ArrayList<>(points.size());
        for (Point2D point : points) {
            Objects.requireNonNull(point, "Точки ломаной не должны быть null");
            this.points.add(copyOf(point));
        }
    }

    private static Point2D copyOf(Point2D point) {
        return new Point2D(point.getX(), point.getY());
    }

    public int getPointCount() {
        return points.size();
    }

    /** Возвращает копию точки по индексу. */
    public Point2D getPoint(int index) {
        return copyOf(points.get(index));
    }

    /** Заменяет точку ломаной; индекс проверяется стандартным списком. */
    public void setPoint(int index, Point2D point) {
        Objects.requireNonNull(point, "Точка не должна быть null");
        points.set(index, copyOf(point));
    }

    public void setPoint(int index, int x, int y) {
        points.set(index, new Point2D(x, y));
    }

    /** Возвращает независимую копию всех точек ломаной. */
    public List<Point2D> getPoints() {
        List<Point2D> result = new ArrayList<>(points.size());
        for (Point2D point : points) {
            result.add(copyOf(point));
        }
        return List.copyOf(result);
    }

    /** Сумма длин отрезков между соседними точками. */
    public double length() {
        double total = 0.0;
        for (int i = 1; i < points.size(); i++) {
            total += points.get(i - 1).distanceTo(points.get(i));
        }
        return total;
    }

    @Override
    public String toString() {
        return "Ломаная " + points + "; общая длина = " + String.format(Locale.ROOT, "%.3f", length());
    }
}

package ru.abrakov.geometry;

import java.util.List;
import java.util.Objects;

/** Квадрат, задаваемый координатами левого верхнего угла и длиной стороны. */
public class Square {
    private final Point2D topLeft;
    private int side;

    public Square(Point2D topLeft, int side) {
        Objects.requireNonNull(topLeft, "Левый верхний угол обязателен");
        this.topLeft = new Point2D(topLeft.getX(), topLeft.getY());
        validateSide(side);
        this.side = side;
    }

    public Square(int x, int y, int side) {
        this(new Point2D(x, y), side);
    }

    public Point2D getTopLeft() {
        return new Point2D(topLeft.getX(), topLeft.getY());
    }

    public int getSide() {
        return side;
    }

    /** Сторона квадрата всегда должна быть строго положительной. */
    public void setSide(int side) {
        validateSide(side);
        this.side = side;
    }

    private static void validateSide(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Длина стороны квадрата должна быть больше нуля");
        }
    }

    /**
     * Возвращает новую ломаную через четыре вершины квадрата.
     * Последняя точка не соединяется с первой * */

    public PolygonalChain getPolygonalChain() {
        int x = topLeft.getX();
        int y = topLeft.getY();
        int right = Math.addExact(x, side);
        int bottom = Math.addExact(y, side);

        return new PolygonalChain(List.of(
                new Point2D(x, y),
                new Point2D(right, y),
                new Point2D(right, bottom),
                new Point2D(x, bottom)
        ));
    }

    @Override
    public String toString() {
        return "Квадрат в точке " + topLeft + " со стороной " + side;
    }
}

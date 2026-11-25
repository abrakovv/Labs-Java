package ru.abrakov.geometry;

public class Square {
    private final Point2D topLeft;
    private int side;

    public Square(Point2D topLeft, int side) {
        if (topLeft == null) {
            throw new IllegalArgumentException("Левый верхний угол не должен быть null.");
        }
        this.topLeft = topLeft.copy();
        validateSide(side);
        this.side = side;
    }

    public Square(int x, int y, int side) {
        this(new Point2D(x, y), side);
    }

    public Point2D getTopLeft() {
        return topLeft.copy();
    }

    public int getSide() {
        return side;
    }

    public final void setSide(int side) {
        validateSide(side);
        this.side = side;
    }

    private void validateSide(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Сторона квадрата должна быть положительной.");
        }
        try {
            Math.addExact(topLeft.getX(), side);
            Math.addExact(topLeft.getY(), side);
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException(
                    "Координаты квадрата выходят за допустимый диапазон.", exception
            );
        }
    }

    public PolygonalChain getPolygonalChain() {
        int x = topLeft.getX();
        int y = topLeft.getY();
        int right = Math.addExact(x, side);
        int bottom = Math.addExact(y, side);

        return new PolygonalChain(
                new Point2D(x, y),
                new Point2D(right, y),
                new Point2D(right, bottom),
                new Point2D(x, bottom)
        );
    }

    @Override
    public String toString() {
        return "Квадрат в точке " + topLeft + " со стороной " + side;
    }
}

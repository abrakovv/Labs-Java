package ru.abrakov.geometry;

import java.util.Locale;
import java.util.Objects;

/**
 * Отрезок, заданный начальной и конечной точками.
 * Линия хранит собственные копии точек и никогда не разделяет их объекты
 * с переданными извне точками или с другой линией.
 */
public class Line implements Cloneable {
    private Point2D start;
    private Point2D end;

    public Line(Point2D start, Point2D end) {
        Objects.requireNonNull(start, "Начальная точка обязательна");
        Objects.requireNonNull(end, "Конечная точка обязательна");
        this.start = copyOf(start);
        this.end = copyOf(end);
    }

    public Line(int x1, int y1, int x2, int y2) {
        this(new Point2D(x1, y1), new Point2D(x2, y2));
    }

    private static Point2D copyOf(Point2D point) {
        return new Point2D(point.getX(), point.getY());
    }

    /** Возвращает копию начальной точки, а не внутренний объект линии. */
    public Point2D getStart() {
        return copyOf(start);
    }

    /** Возвращает копию конечной точки, а не внутренний объект линии. */
    public Point2D getEnd() {
        return copyOf(end);
    }

    public void setStart(Point2D start) {
        Objects.requireNonNull(start, "Начальная точка обязательна");
        this.start = copyOf(start);
    }

    public void setEnd(Point2D end) {
        Objects.requireNonNull(end, "Конечная точка обязательна");
        this.end = copyOf(end);
    }

    public void setStart(int x, int y) {
        this.start = new Point2D(x, y);
    }

    public void setEnd(int x, int y) {
        this.end = new Point2D(x, y);
    }

    public double length() {
        return start.distanceTo(end);
    }

    /**
     * Линии равны, если совпадают их начальные точки и совпадают конечные точки.
     * Поэтому линия, направленная в обратную сторону, не считается равной.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Line other)) {
            return false;
        }
        return start.equals(other.start) && end.equals(other.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    /** Создает независимую копию линии вместе с отдельными объектами точек. */
    @Override
    public Line clone() {
        return new Line(start, end);
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end
                + "; длина = " + String.format(Locale.ROOT, "%.3f", length());
    }
}

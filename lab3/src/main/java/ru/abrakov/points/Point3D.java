package ru.abrakov.points;

import ru.abrakov.geometry.Point2D;

import java.util.Objects;

/** Трехмерная точка — наследник двумерной точки с дополнительной координатой Z. */
public class Point3D extends Point2D {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    /** Евклидово расстояние между двумя трехмерными точками. */
    public double distanceTo(Point3D other) {
        Objects.requireNonNull(other, "Вторая точка не должна быть null");
        double dx = (double) getX() - other.getX();
        double dy = (double) getY() - other.getY();
        double dz = (double) z - other.z;
        return Math.hypot(Math.hypot(dx, dy), dz);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Point3D other)) {
            return false;
        }
        return getX() == other.getX() && getY() == other.getY() && z == other.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), z);
    }

    @Override
    public String toString() {
        return "{" + getX() + "; " + getY() + "; " + z + "}";
    }
}

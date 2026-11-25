package ru.abrakov.points;

import ru.abrakov.geometry.Point2D;

public class Point3D extends Point2D {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Point3D(Point3D other) {
        this(requirePoint(other).getX(), other.getY(), other.getZ());
    }

    private static Point3D requirePoint(Point3D point) {
        if (point == null) {
            throw new IllegalArgumentException("Точка не должна быть null.");
        }
        return point;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setCoordinates(int x, int y, int z) {
        super.setCoordinates(x, y);
        this.z = z;
    }

    @Override
    public String toString() {
        return "{" + getX() + "; " + getY() + "; " + z + "}";
    }
}

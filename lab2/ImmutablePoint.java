public final class ImmutablePoint {
    private final double x;
    private final double y;

    public ImmutablePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    @Override
    public String toString() {
        return "{" + format(x) + ";" + format(y) + "}";
    }

    private static String format(double value) {
        return value == Math.rint(value) && Math.abs(value) < 1.0e15
                ? Long.toString((long) value) : Double.toString(value);
    }
}

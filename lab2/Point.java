public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + format(x) + ";" + format(y) + "}";
    }

    private static String format(double value) {
        return value == Math.rint(value) && Math.abs(value) < 1.0e15
                ? Long.toString((long) value) : Double.toString(value);
    }
}

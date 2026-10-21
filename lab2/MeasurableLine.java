public class MeasurableLine extends ImmutableLine {
    public MeasurableLine(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public int length() {
        double dx = getEnd().getX() - getStart().getX();
        double dy = getEnd().getY() - getStart().getY();
        return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

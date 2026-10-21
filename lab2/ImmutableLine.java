public class ImmutableLine {
    private final ImmutablePoint start;
    private final ImmutablePoint end;

    public ImmutableLine(ImmutablePoint start, ImmutablePoint end) {
        if (start == null || end == null) throw new IllegalArgumentException("Точки линии не могут быть null");
        this.start = start;
        this.end = end;
    }

    public ImmutableLine(double x1, double y1, double x2, double y2) {
        this(new ImmutablePoint(x1, y1), new ImmutablePoint(x2, y2));
    }

    public ImmutablePoint getStart() { return start; }
    public ImmutablePoint getEnd() { return end; }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }
}

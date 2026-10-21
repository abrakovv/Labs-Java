public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        if (start == null || end == null) throw new IllegalArgumentException("Точки линии не могут быть null");
        this.start = start;
        this.end = end;
    }

    public Point getStart() { return start; }
    public Point getEnd() { return end; }
    public void setStart(Point start) {
        if (start == null) throw new IllegalArgumentException("Начальная точка не может быть null");
        this.start = start;
    }
    public void setEnd(Point end) {
        if (end == null) throw new IllegalArgumentException("Конечная точка не может быть null");
        this.end = end;
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }
}

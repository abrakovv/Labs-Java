import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private final Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
    }

    private void task11() {
        heading("Задание 1.1. Точка");
        Point[] points = new Point[3];
        for (int i = 0; i < points.length; i++) {
            while (true) {
                System.out.println("Введите координаты точки " + (i + 1) + ":");
                Point point = readPoint();
                if (isDuplicate(points, point)) {
                    System.out.println("Такая точка уже есть. Введите другие координаты.\n");
                } else {
                    points[i] = point;
                    break;
                }
            }
        }
        System.out.println("Созданные точки:");
        for (int i = 0; i < points.length; i++) {
            System.out.println("Точка " + (i + 1) + ": " + points[i]);
        }
    }

    private void task21() {
        heading("Задание 2.1. Линия и ссылки на точки");
        System.out.println("Введите координаты начала и конца первой линии:");
        Line line1 = new Line(readPoint(), readPoint());
        System.out.println("Введите координаты начала и конца второй линии:");
        Line line2 = new Line(readPoint(), readPoint());
        Line line3 = new Line(line1.getStart(), line2.getEnd());

        System.out.println("\nИсходные линии:");
        printLines(line1, line2, line3);

        System.out.println("\nИзменим координаты существующих точек, на которые ссылается линия 3.");
        System.out.println("Новые координаты начала линии 1:");
        line1.getStart().moveTo(readDouble("X: "), readDouble("Y: "));
        System.out.println("Новые координаты конца линии 2:");
        line2.getEnd().moveTo(readDouble("X: "), readDouble("Y: "));
        printLines(line1, line2, line3);

        System.out.println("\nТеперь заменим начало линии 1 новым объектом Point.");
        Point replacement = readPoint();
        line1.setStart(replacement);
        printLines(line1, line2, line3);
        System.out.println("Линия 3 сохраняет прежнее начало, так как ссылается на старый объект точки.");
    }

    private void task31() {
        heading("Задание 3.1. Студент и массив оценок");
        String firstName = readName("Имя первого студента: ");
        int[] grades = readGrades();
        Student first = new Student(firstName, grades);
        String secondName = readName("Имя второго студента: ");
        Student second = new Student(secondName, first.getGrades());

        System.out.println("\nДо изменения оценок:");
        System.out.println(first);
        System.out.println(second);
        System.out.println("Изменим оценку через второго студента. Массив у них общий.");
        int index = readInt("Номер оценки для изменения (от 1 до " + grades.length + "): ", 1, grades.length) - 1;
        int newGrade = readInt("Новая оценка (2–5): ", 2, 5);
        second.setGrade(index, newGrade);
        System.out.println("После изменения:");
        System.out.println(first);
        System.out.println(second);

        String thirdName = readName("Имя третьего студента: ");
        Student third = new Student(thirdName, Arrays.copyOf(first.getGrades(), first.getGrades().length));
        System.out.println("\nТретий студент получил отдельную копию массива:");
        System.out.println(first);
        System.out.println(third);
        int firstGrade = readInt("Введите новую первую оценку студента " + firstName + " (2–5): ", 2, 5);
        first.setGrade(0, firstGrade);
        System.out.println("После изменения оценки первого студента:");
        System.out.println(first);
        System.out.println(third);
    }

    private void task41() {
        heading("Задание 4.1. Неизменяемая точка");
        ImmutablePoint[] points = new ImmutablePoint[3];
        for (int i = 0; i < points.length; i++) {
            System.out.println("Введите координаты неизменяемой точки " + (i + 1) + ":");
            Point p = readPoint();
            points[i] = new ImmutablePoint(p.getX(), p.getY());
        }
        System.out.println("\nСозданные неизменяемые точки:");
        for (int i = 0; i < points.length; i++) {
            System.out.println("Точка " + (i + 1) + ": " + points[i]);
        }
        System.out.println("\nЧтобы получить другие координаты, создадим новый объект.");
        ImmutablePoint original = points[0];
        ImmutablePoint replacement = new ImmutablePoint(readDouble("Новое X: "), readDouble("Новое Y: "));
        System.out.println("Исходная точка: " + original);
        System.out.println("Новая точка:    " + replacement);
        System.out.println("Исходный объект не изменился: " + original);
    }

    private void task42() {
        heading("Задание 4.2. Линия с неизменяемыми точками");
        System.out.println("Введите четыре координаты первой линии:");
        ImmutableLine line1 = new ImmutableLine(
                readDouble("X начала: "), readDouble("Y начала: "),
                readDouble("X конца: "), readDouble("Y конца: "));
        System.out.println("Введите координаты начала второй линии:");
        ImmutablePoint start2 = new ImmutablePoint(readDouble("X начала: "), readDouble("Y начала: "));
        System.out.println("Введите координаты конца второй линии:");
        ImmutablePoint end2 = new ImmutablePoint(readDouble("X конца: "), readDouble("Y конца: "));
        ImmutableLine line2 = new ImmutableLine(start2, end2);
        ImmutableLine line3 = new ImmutableLine(line1.getStart(), line2.getEnd());

        System.out.println("\nПервая линия (создана по четырём координатам): " + line1);
        System.out.println("Вторая линия (создана по двум точкам):       " + line2);
        System.out.println("Третья линия (начало первой, конец второй):  " + line3);
        System.out.println("\nСоздадим новую первую линию с другим началом.");
        ImmutablePoint newStart = new ImmutablePoint(readDouble("Новое X начала: "), readDouble("Новое Y начала: "));
        ImmutableLine newLine1 = new ImmutableLine(newStart, line1.getEnd());
        System.out.println("Старая первая линия: " + line1);
        System.out.println("Новая первая линия:  " + newLine1);
        System.out.println("Третья линия не изменилась: " + line3);
    }

    private void task53() {
        heading("Задание 5.3. Длина линии");
        System.out.println("Введите координаты начала линии:");
        double x1 = readDouble("X: ");
        double y1 = readDouble("Y: ");
        System.out.println("Введите координаты конца линии:");
        double x2 = readDouble("X: ");
        double y2 = readDouble("Y: ");
        MeasurableLine line = new MeasurableLine(x1, y1, x2, y2);
        double dx = line.getEnd().getX() - line.getStart().getX();
        double dy = line.getEnd().getY() - line.getStart().getY();
        double exactLength = Math.sqrt(dx * dx + dy * dy);
        System.out.println("\n" + line);
        System.out.println("Формула: длина = sqrt((x2 - x1)^2 + (y2 - y1)^2)");
        System.out.printf(Locale.US, "Подстановка: sqrt((%.4f - %.4f)^2 + (%.4f - %.4f)^2) = %.4f%n",
                x2, x1, y2, y1, exactLength);
        System.out.println("Длина, округлённая до целого: " + line.length());
    }

    private Point readPoint() {
        return new Point(readDouble("X: "), readDouble("Y: "));
    }

    private int[] readGrades() {
        int count = readInt("Количество оценок (1–20): ", 1, 20);
        int[] grades = new int[count];
        for (int i = 0; i < count; i++) {
            grades[i] = readInt("Оценка " + (i + 1) + " (2–5): ", 2, 5);
        }
        return grades;
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim().replace(',', '.');
            try {
                double value = Double.parseDouble(text);
                if (Double.isFinite(value)) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Ошибка: введите корректное число, например 3 или -2.5.");
        }
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            double value = readDouble(prompt);
            if (value == Math.rint(value) && value >= min && value <= max) return (int) value;
            System.out.println("Ошибка: введите целое число от " + min + " до " + max + ".");
        }
    }

    private String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim();
            if (name.matches("[\\p{L}][\\p{L} '-]*")) return name;
            System.out.println("Ошибка: введите непустое имя, используя буквы, пробел, дефис или апостроф.");
        }
    }

    private boolean isDuplicate(Point[] points, Point candidate) {
        for (Point point : points) {
            if (point != null && point.getX() == candidate.getX() && point.getY() == candidate.getY()) return true;
        }
        return false;
    }

    private void printLines(Line first, Line second, Line third) {
        System.out.println("Линия 1: " + first);
        System.out.println("Линия 2: " + second);
        System.out.println("Линия 3: " + third);
    }

    private void heading(String title) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }

    public void run() {
        System.out.println("Лабораторная работа № 2, вариант 1");
        task11();
        task21();
        task31();
        task41();
        task42();
        task53();

    }

    @Override
    public String toString() {
        return "Главный класс запуска лабораторной работы № 2";
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

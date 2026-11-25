package ru.abrakov.main;

import ru.abrakov.collections.ImmutableIntList;
import ru.abrakov.geometry.Line;
import ru.abrakov.geometry.Point2D;
import ru.abrakov.geometry.PolygonalChain;
import ru.abrakov.geometry.Square;
import ru.abrakov.points.AbstractPoint;
import ru.abrakov.points.ColoredPoint;
import ru.abrakov.points.ColoredTimedPoint;
import ru.abrakov.points.Point3D;
import ru.abrakov.points.TimedPoint;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private final Scanner scanner;
    private final String title;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.title = "Лабораторная работа № 3. Вариант 1";
    }

    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        printHeader();

        task1SeparateLines();
        task1Square();
        task2ImmutableList();
        task3Point3D();
        task4FlexiblePoints();
        task5Addition();
        Line lineForClone = task6CompareLines();
        task7Packages();
        task7MainPackageDemo();
        task7Power(args);
        task8CloneLine(lineForClone);

        System.out.println("\nРабота завершена.");
    }

    private void printHeader() {
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }

    private void task1SeparateLines() {
        section("Задание 1. Инкапсуляция", "Задача 6. Отдельные линии");

        Point2D commonStart = new Point2D(
                readInt("Координата X общей начальной точки: "),
                readInt("Координата Y общей начальной точки: ")
        );
        Point2D firstEnd = new Point2D(
                readInt("Координата X конца первой линии: "),
                readInt("Координата Y конца первой линии: ")
        );
        Point2D secondEnd = new Point2D(
                readInt("Координата X конца второй линии: "),
                readInt("Координата Y конца второй линии: ")
        );

        Line first = new Line(commonStart, firstEnd);
        Line second = new Line(commonStart, secondEnd);

        System.out.println("Первая линия: " + first);
        System.out.println("Вторая линия: " + second);

        System.out.println("Изменим исходную точку, которую передали обеим линиям.");
        commonStart.setCoordinates(
                readInt("Новое значение X исходной точки: "),
                readInt("Новое значение Y исходной точки: ")
        );
        System.out.println("Первая линия после изменения исходной точки: " + first);
        System.out.println("Вторая линия после изменения исходной точки: " + second);

        System.out.println("Изменим начало и конец первой линии.");
        first.setStart(new Point2D(
                readInt("Новое X начала первой линии: "),
                readInt("Новое Y начала первой линии: ")
        ));
        first.setEnd(new Point2D(
                readInt("Новое X конца первой линии: "),
                readInt("Новое Y конца первой линии: ")
        ));

        System.out.println("Первая линия: " + first);
        System.out.println("Начало линии: " + first.getStart());
        System.out.println("Конец линии: " + first.getEnd());

        Point2D receivedStart = first.getStart();
        receivedStart.setCoordinates(100, 100);
        System.out.println("Изменение полученной точки не меняет линию: " + first);

        System.out.printf(Locale.US, "Длина первой линии: %.3f%n", first.length());
    }

    private void task1Square() {
        section("Задание 1. Инкапсуляция", "Задача 12. Квадрат");

        Square squareByPoint = new Square(new Point2D(5, 3), 23);
        Square square = new Square(5, 3, 23);
        System.out.println("Квадрат, созданный с помощью точки: " + squareByPoint);
        System.out.println("Квадрат, созданный с помощью координат x и y: " + square);
        System.out.println("Левый верхний угол: " + square.getTopLeft());
        System.out.println("Сторона квадрата: " + square.getSide());

        PolygonalChain chain = square.getPolygonalChain();
        System.out.println(chain);
        System.out.printf(Locale.US, "Длина ломаной: %.3f%n", chain.length());

        int lastPoint = chain.getPointCount() - 1;
        chain.setPoint(lastPoint, new Point2D(15, 25));

        System.out.println("Последняя точка перемещена в {15; 25}.");
        System.out.println(chain);
        System.out.println("Последняя точка: " + chain.getPoint(lastPoint));
        System.out.printf(Locale.US, "Новая длина ломаной: %.3f%n", chain.length());

        try {
            new Square(5, 3, 0);
        } catch (IllegalArgumentException exception) {
            System.out.println("Проверка конструктора с нулевой стороной: " + exception.getMessage());
        }

        try {
            new Square(5, 3, -1);
        } catch (IllegalArgumentException exception) {
            System.out.println("Проверка конструктора с отрицательной стороной: " + exception.getMessage());
        }

        try {
            square.setSide(0);
        } catch (IllegalArgumentException exception) {
            System.out.println("Проверка изменения стороны на 0: " + exception.getMessage());
        }

        try {
            square.setSide(-1);
        } catch (IllegalArgumentException exception) {
            System.out.println("Проверка изменения стороны на отрицательное значение: " + exception.getMessage());
        }

        square.setSide(24);
        System.out.println("После изменения стороны: " + square);
        System.out.println("Текущая длина стороны: " + square.getSide());
    }

    private void task2ImmutableList() {
        section("Задание 2. Разработка структур данных", "Задача 1. Неизменяемый массив");

        System.out.println("Создание списка из массива целых чисел.");
        System.out.println("Чтобы проверить пустой список, нажмите Enter. Для непустого списка введите числа.");
        int[] array = readIntArray("Введите элементы через пробел или запятую: ", 0, 50);
        ImmutableIntList arrayList = new ImmutableIntList(array);
        System.out.println("Список: " + arrayList);
        System.out.println("Размер списка: " + arrayList.size());
        System.out.println("Список пуст: " + arrayList.isEmpty());

        System.out.println("\nСоздание списка с передачей чисел как отдельных значений.");
        int[] separateValues = readIntArray("Введите значения через пробел или запятую: ", 1, 50);
        ImmutableIntList valuesList = new ImmutableIntList(
                separateValues[0], Arrays.copyOfRange(separateValues, 1, separateValues.length)
        );
        System.out.println("Список: " + valuesList);

        ImmutableIntList copiedList = new ImmutableIntList(valuesList);
        System.out.println("Копия другого списка: " + copiedList);

        int index = readIntInRange(
                "Введите индекс элемента для чтения и замены (от 0 до "
                        + (valuesList.size() - 1) + "): ",
                0,
                valuesList.size() - 1
        );
        System.out.println("Элемент с индексом " + index + ": " + valuesList.get(index));
        valuesList.set(index, readInt("Введите новое значение: "));
        System.out.println("Список после замены: " + valuesList);
        System.out.println("Исходная копия не изменилась: " + copiedList);

        int[] resultArray = valuesList.toArray();
        System.out.println("Массив, полученный из списка: " + Arrays.toString(resultArray));
        resultArray[0] = resultArray[0] + 100;
        System.out.println("Полученный массив после изменения: " + Arrays.toString(resultArray));
        System.out.println("Сам список после изменения массива: " + valuesList);

        try {
            valuesList.get(valuesList.size());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Проверка неверного индекса при чтении: " + exception.getMessage());
        }

        try {
            valuesList.set(valuesList.size(), 0);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Проверка неверного индекса при замене: " + exception.getMessage());
        }
    }

    private void task3Point3D() {
        section("Задание 3. Наследование", "Задача 5. Трехмерная точка");

        Point3D point = new Point3D(
                readInt("Координата X: "),
                readInt("Координата Y: "),
                readInt("Координата Z: ")
        );
        System.out.println("Трехмерная точка: " + point);
        System.out.println("Координата Z: " + point.getZ());

        point.setZ(readInt("Введите новое значение Z: "));
        System.out.println("Точка после изменения Z: " + point);

        Point2D point2D = point;
        System.out.println("Та же точка через ссылку Point2D: " + point2D);
    }

    private void task4FlexiblePoints() {
        section("Задание 4. Создание иерархий", "Задача 5. Точки");

        System.out.println("Точка с одной координатой и цветом.");
        int x1 = readInt("Координата X: ");
        String color1 = readText("Цвет точки: ");
        ColoredPoint first = new ColoredPoint(x1, color1);
        System.out.println(first);
        System.out.println("Координаты: " + Arrays.toString(first.getCoordinates()));
        System.out.println("Цвет: " + first.getColor());

        System.out.println("\nТочка с тремя координатами и временем появления.");
        int x2 = readInt("Координата X: ");
        int y2 = readInt("Координата Y: ");
        int z2 = readInt("Координата Z: ");
        String time2 = readTime("Время появления (ЧЧ:ММ): ");
        TimedPoint second = new TimedPoint(new int[]{x2, y2, z2}, time2);
        System.out.println(second);
        System.out.println("Время появления: " + second.getAppearanceTime());

        System.out.println("\nТочка с двумя координатами, временем появления и цветом.");
        int x3 = readInt("Координата X: ");
        int y3 = readInt("Координата Y: ");
        String time3 = readTime("Время появления (ЧЧ:ММ): ");
        String color3 = readText("Цвет точки: ");
        ColoredTimedPoint third = new ColoredTimedPoint(new int[]{x3, y3}, time3, color3);
        System.out.println(third);
        System.out.println("Цвет: " + third.getColor());
        System.out.println("Время появления: " + third.getAppearanceTime());

        AbstractPoint[] points = {first, second, third};
        System.out.println("\nТочки через массив типа AbstractPoint:");
        for (AbstractPoint point : points) {
            System.out.println(point);
        }
    }

    private void task5Addition() {
        section("Задание 5. Полиморфизм", "Задача 1. Сложение");

        SumUtils sum = new SumUtils();
        System.out.println("Класс: " + sum);

        System.out.printf(Locale.US, "2 + 3/5 + 2.3 = %.6f%n",
                sum.sum(2.0, 3.0 / 5.0, 2.3));
        System.out.printf(Locale.US, "3.6 + 49/12 + 3 + 3/2 = %.6f%n",
                sum.sum(3.6, 49.0 / 12.0, 3.0, 3.0 / 2.0));
        System.out.printf(Locale.US, "1/3 + 1 = %.6f%n",
                sum.sum(1.0 / 3.0, 1.0));
    }

    private Line task6CompareLines() {
        section("Задание 6. Полиморфное сравнение", "Задача 3. Сравнение линий");

        Line first = new Line(
                readInt("X начала линии: "),
                readInt("Y начала линии: "),
                readInt("X конца линии: "),
                readInt("Y конца линии: ")
        );
        Line second = new Line(first.getStart(), first.getEnd());
        Line reversed = new Line(first.getEnd(), first.getStart());

        System.out.println("Первая линия: " + first);
        System.out.println("Вторая линия с такими же координатами: " + second);
        System.out.println("Первая и вторая линии равны: " + first.equals(second));
        System.out.println("Их hashCode совпадает: " + (first.hashCode() == second.hashCode()));
        System.out.println("Линия с обратным направлением: " + reversed);
        System.out.println("Первая и обратная линии равны: " + first.equals(reversed));

        return first;
    }

    private void task7Packages() {
        section("Задание 7. Пакеты", "Задача 1. Навести порядок");
        System.out.println("ru.abrakov.collections — ImmutableIntList");
        System.out.println("ru.abrakov.geometry — Point2D, Line, PolygonalChain, Square");
        System.out.println("ru.abrakov.points — Point3D, AbstractPoint, ColoredPoint, TimedPoint, ColoredTimedPoint");
        System.out.println("ru.abrakov.main — Main, SumUtils, PowerCalculator");
    }

    private void task7MainPackageDemo() {
        section("Задание 7. Пакеты", "Задача 2. Главный метод");

        System.out.println("Главный класс: Main, пакет ru.abrakov.main.");
        System.out.println("Класс SumUtils находится в том же пакете.");
        System.out.println("Введите числа для проверки метода сложения.");

        int count = readIntInRange("Количество чисел (1–50): ", 1, 50);
        double[] numbers = new double[count];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = readDouble("Число " + (i + 1) + ": ");
        }

        SumUtils sum = new SumUtils();
        double result = sum.sum(numbers);

        System.out.println("Вызван метод SumUtils.sum() из главного класса Main.");
        System.out.println("Результат сложения: " + result);
    }

    private void task7Power(String[] args) {
        section("Задание 7. Пакеты", "Задача 3. Возведение в степень");

        String x;
        String y;
        if (args != null && args.length == 2) {
            x = args[0];
            y = args[1];
            System.out.println("Числа получены из аргументов командной строки: X = " + x + ", Y = " + y);
        } else {
            System.out.println("Для запуска через командную строку укажите два параметра: X и Y.");
            x = readText("Введите целое число X: ");
            y = readText("Введите целое число Y: ");
        }

        PowerCalculator calculator = new PowerCalculator();
        while (true) {
            try {
                System.out.println(calculator);
                System.out.println(x + " в степени " + y + " = " + calculator.calculate(x, y));
                break;
            } catch (NumberFormatException exception) {
                System.out.println("X и Y должны быть целыми числами в диапазоне int.");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
            x = readText("Повторно введите X: ");
            y = readText("Повторно введите Y: ");
        }
    }

    private void task8CloneLine(Line original) {
        section("Задание 8. Пакетная инкапсуляция", "Задача 5. Клонирование линии");

        Line copy = original.clone();
        System.out.println("Исходная линия: " + original);
        System.out.println("Копия линии: " + copy);
        System.out.println("До изменения линии равны: " + original.equals(copy));

        Point2D originalStart = original.getStart();
        int newX;
        int newY;
        do {
            newX = readInt("Новое X начала копии: ");
            newY = readInt("Новое Y начала копии: ");
            if (newX == originalStart.getX() && newY == originalStart.getY()) {
                System.out.println("Введите координаты, отличающиеся от начала исходной линии.");
            }
        } while (newX == originalStart.getX() && newY == originalStart.getY());

        copy.setStart(new Point2D(newX, newY));
        System.out.println("Измененная копия: " + copy);
        System.out.println("Исходная линия: " + original);
        System.out.println("Исходная линия не изменилась: " + !original.equals(copy));
    }

    private int[] readIntArray(String prompt, int minCount, int maxCount) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                if (minCount == 0) {
                    return new int[0];
                }
                System.out.println("Нужно ввести хотя бы одно число.");
                continue;
            }

            String[] parts = line.split("[,;\\s]+");
            if (parts.length == 0 || parts.length < minCount || parts.length > maxCount) {
                System.out.println("Количество чисел должно быть от " + minCount + " до " + maxCount + ".");
                continue;
            }

            int[] result = new int[parts.length];
            boolean valid = true;
            for (int i = 0; i < parts.length; i++) {
                try {
                    result[i] = Integer.parseInt(parts[i]);
                } catch (NumberFormatException exception) {
                    System.out.println("Значение \"" + parts[i] + "\" не является целым числом.");
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return result;
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                System.out.println("Введите целое число.");
            }
        }
    }

    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Введите число от " + min + " до " + max + ".");
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().replace(',', '.');
            try {
                double number = Double.parseDouble(value);
                if (Double.isFinite(number)) {
                    return number;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Введите корректное вещественное число.");
                continue;
            }
            System.out.println("Число должно быть конечным.");
        }
    }

    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Строка не должна быть пустой.");
        }
    }

    private String readTime(String prompt) {
        while (true) {
            String value = readText(prompt);
            if (AbstractPoint.isValidTime(value)) {
                return value;
            }
            System.out.println("Введите время в формате ЧЧ:ММ от 00:00 до 23:59.");
        }
    }

    private void section(String task, String subtask) {
        System.out.println("\n--------------------------------------------------");
        System.out.println(task);
        System.out.println(subtask);
        System.out.println("--------------------------------------------------");
    }

    @Override
    public String toString() {
        return title;
    }
}

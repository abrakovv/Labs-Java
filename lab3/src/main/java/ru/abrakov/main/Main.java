package ru.abrakov.main;

import ru.abrakov.collections.ImmutableIntList;
import ru.abrakov.geometry.Line;
import ru.abrakov.geometry.Point2D;
import ru.abrakov.geometry.PolygonalChain;
import ru.abrakov.geometry.Square;
import ru.abrakov.points.FlexiblePoint;
import ru.abrakov.points.Point3D;

import java.util.Scanner;

/** Единственная точка входа. Последовательно демонстрирует все задачи варианта № 1. */
public class Main {
    private final Scanner input;

    public Main() {
        input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        printHeader();
        demonstrateSeparateLines();
        demonstrateSquare();
        demonstrateImmutableIntList();
        demonstratePoint3D();
        demonstrateFlexiblePoints();
        demonstrateAddition();
        demonstrateLineEqualsAndClone();
        demonstratePower(args);
        System.out.println("\nВсе демонстрации лабораторной работы завершены.");
    }

    private void printHeader() {
        System.out.println("============================================================");
        System.out.println("       ЛАБОРАТОРНАЯ РАБОТА № 3 — ВАРИАНТ 1");
        System.out.println("       Объектно-ориентированное программирование");
        System.out.println("============================================================");
    }

    /** Задание 1.6: линии не должны разделять объекты своих точек. */
    private void demonstrateSeparateLines() {
        section("1. Задание 1.6 — отдельные линии");

        Point2D sharedStart = new Point2D(
                readInt("Введите X общей исходной точки: "),
                readInt("Введите Y общей исходной точки: ")
        );
        Point2D firstEnd = new Point2D(
                readInt("Введите X конца первой линии: "),
                readInt("Введите Y конца первой линии: ")
        );
        Point2D secondEnd = new Point2D(
                readInt("Введите X конца второй линии: "),
                readInt("Введите Y конца второй линии: ")
        );

        // Обеим линиям передается один и тот же объект sharedStart.
        // Конструктор Line обязан скопировать его, а не сохранить общую ссылку.
        Line firstLine = new Line(sharedStart, firstEnd);
        Line secondLine = new Line(sharedStart, secondEnd);

        System.out.println("Первая линия:  " + firstLine);
        System.out.println("Вторая линия:  " + secondLine);

        sharedStart.setCoordinates(10_000, 10_000);
        System.out.println("После изменения исходной общей точки:");
        System.out.println("Первая линия не изменилась: " + firstLine);
        System.out.println("Вторая линия не изменилась: " + secondLine);

        System.out.println("\nИзменение координат первой линии ее методами:");
        firstLine.setStart(
                readInt("Новая X начальной точки первой линии: "),
                readInt("Новая Y начальной точки первой линии: ")
        );
        firstLine.setEnd(
                readInt("Новая X конечной точки первой линии: "),
                readInt("Новая Y конечной точки первой линии: ")
        );
        System.out.println("Обновленная первая линия: " + firstLine);
        System.out.println("Начало: " + firstLine.getStart());
        System.out.println("Конец:  " + firstLine.getEnd());

        Point2D externalCopy = firstLine.getStart();
        externalCopy.setCoordinates(-10_000, -10_000);
        System.out.println("Изменение точки, полученной через getter, не меняет линию: " + firstLine);
    }

    /** Задание 1.12: заданный в методичке квадрат и полученная из него ломаная. */
    private void demonstrateSquare() {
        section("2. Задание 1.12 — квадрат и ломаная");

        // Координаты и сторона фиксированы условием задания: {5; 3}, 23.
        Square square = new Square(5, 3, 23);
        System.out.println(square);
        System.out.println("Получение размера стороны через getter: " + square.getSide());

        PolygonalChain chain = square.getPolygonalChain();
        System.out.println("Ломаная, полученная из квадрата: " + chain);
        System.out.println("Последняя точка ломаной: " + chain.getPoint(chain.getPointCount() - 1));
        System.out.printf("Исходная длина ломаной: %.3f%n", chain.length());

        // В методичке требуется переместить последнюю точку именно в {15; 25}.
        chain.setPoint(chain.getPointCount() - 1, 15, 25);
        System.out.println("После переноса последней точки в {15; 25}: " + chain);
        System.out.printf("Новая длина ломаной: %.3f%n", chain.length());

        System.out.println("Проверка возможности изменить сторону квадрата:");
        square.setSide(24);
        System.out.println("После изменения стороны: " + square + "; getter = " + square.getSide());
        square.setSide(23); // Возвращаем исходное значение для сохранения условия задания.

        System.out.println("Проверка запрета отрицательной стороны при создании:");
        try {
            new Square(5, 3, -1);
        } catch (IllegalArgumentException exception) {
            System.out.println("Некорректный квадрат не создан: " + exception.getMessage());
        }

        System.out.println("Проверка запрета нулевой стороны при изменении:");
        try {
            square.setSide(0);
        } catch (IllegalArgumentException exception) {
            System.out.println("Некорректный размер отклонен: " + exception.getMessage());
        }
        System.out.println("Квадрат сохранил прежнюю сторону: " + square);
    }

    /** Задание 2.1: обертка над массивом целых чисел. */
    private void demonstrateImmutableIntList() {
        section("3. Задание 2.1 — неизменяемый список значений");

        int count = readIntInRange("Сколько целых чисел добавить в список (0–50): ", 0, 50);
        int[] sourceValues = new int[count];
        for (int i = 0; i < count; i++) {
            sourceValues[i] = readInt("Значение №" + (i + 1) + ": ");
        }

        ImmutableIntList values = new ImmutableIntList(sourceValues);
        System.out.println("Список, созданный из массива: " + values);
        System.out.println("Проверка пустого списка: " + new ImmutableIntList().isEmpty());

        System.out.println("\nПроверка конструктора с отдельными значениями через запятую:");
        int firstSeparate = readInt("Первое число: ");
        int secondSeparate = readInt("Второе число: ");
        int thirdSeparate = readInt("Третье число: ");
        ImmutableIntList separateValues = new ImmutableIntList(firstSeparate, secondSeparate, thirdSeparate);
        System.out.println("Список из отдельных значений: " + separateValues);

        System.out.println("\nОсновной список: " + values);
        System.out.println("Размер: " + values.size());
        System.out.println("Список пуст: " + values.isEmpty());

        if (!values.isEmpty()) {
            int index = readIntInRange("Индекс элемента для чтения (" + (values.size() - 1) + "): ",
                    0, values.size() - 1);
            System.out.println("Элемент с индексом " + index + ": " + values.get(index));

            int newValue = readInt("Новое значение для элемента с индексом " + index + ": ");
            values.set(index, newValue);
            System.out.println("Список после замены: " + values);
        } else {
            System.out.println("Список пуст: чтение и замена элементов не выполняются.");
        }

        int[] returnedArray = values.toArray();
        System.out.println("Содержимое стандартного массива: " + java.util.Arrays.toString(returnedArray));
        if (returnedArray.length > 0) {
            returnedArray[0] = 999_999;
            System.out.println("Изменили полученный массив: " + java.util.Arrays.toString(returnedArray));
            System.out.println("Внутренний список при этом не изменился: " + values);
        }

        ImmutableIntList copied = new ImmutableIntList(values);
        System.out.println("Список, созданный копирующим конструктором: " + copied);

        try {
            values.get(-1);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Проверка неверного индекса сработала: " + exception.getMessage());
        }
    }

    /** Задание 3.5: трехмерная точка наследуется от двумерной. */
    private void demonstratePoint3D() {
        section("4. Задание 3.5 — трехмерная точка");

        Point3D first = new Point3D(
                readInt("Точка A — X: "),
                readInt("Точка A — Y: "),
                readInt("Точка A — Z: ")
        );
        Point3D second = new Point3D(
                readInt("Точка B — X: "),
                readInt("Точка B — Y: "),
                readInt("Точка B — Z: ")
        );

        System.out.println("Точка A: " + first);
        System.out.println("Точка B: " + second);
        System.out.println("Координаты точки A через getters: X=" + first.getX()
                + ", Y=" + first.getY() + ", Z=" + first.getZ());
        System.out.printf("Расстояние между точками: %.3f%n", first.distanceTo(second));

        first.setZ(readInt("Введите новое значение Z для точки A: "));
        System.out.println("Точка A после изменения координаты Z: " + first);

        // Экземпляр трехмерной точки можно хранить в ссылке базового типа Point2D.
        Point2D baseReference = first;
        System.out.println("Та же точка через ссылку базового класса: " + baseReference);
    }

    /** Задание 4.5: координаты и дополнительные характеристики в разных сочетаниях. */
    private void demonstrateFlexiblePoints() {
        section("5. Задание 4.5 — точки с разными характеристиками");

        System.out.println("Точка с одной координатой и цветом:");
        int x1 = readInt("X: ");
        String color1 = readText("Цвет: ");
        FlexiblePoint oneCoordinate = new FlexiblePoint(x1, color1);
        System.out.println(oneCoordinate);

        System.out.println("\nТочка с тремя координатами и временем появления:");
        int x2 = readInt("X: ");
        int y2 = readInt("Y: ");
        int z2 = readInt("Z: ");
        String time2 = readText("Время появления (например, 11:00): ");
        FlexiblePoint threeCoordinates = new FlexiblePoint(x2, y2, z2, null, time2);
        System.out.println(threeCoordinates);

        System.out.println("\nТочка с двумя координатами, временем и цветом:");
        int x3 = readInt("X: ");
        int y3 = readInt("Y: ");
        String time3 = readText("Время появления (например, 15:35): ");
        String color3 = readText("Цвет: ");
        FlexiblePoint twoCoordinates = new FlexiblePoint(x3, y3, color3, time3);
        System.out.println(twoCoordinates);
    }

    /** Задание 5.1: сумма любого количества чисел и расчеты из методички. */
    private void demonstrateAddition() {
        section("6. Задание 5.1 — сложение");

        SumUtils sumUtils = new SumUtils();
        System.out.println(sumUtils);
        System.out.printf("1) 2 + 3/5 + 2.3 = %.6f%n", sumUtils.sum(2.0, 3.0 / 5.0, 2.3));
        System.out.printf("2) 3.6 + 49/12 + 3 + 3/2 = %.6f%n",
                sumUtils.sum(3.6, 49.0 / 12.0, 3.0, 3.0 / 2.0));
        System.out.printf("3) 1/3 + 1 = %.6f%n", sumUtils.sum(1.0 / 3.0, 1.0));

        int count = readIntInRange("Сколько дополнительных чисел сложить (0–50): ", 0, 50);
        double[] numbers = new double[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = readDouble("Число №" + (i + 1) + ": ");
        }
        if (count > 0) {
            System.out.printf("Сумма введенных чисел: %.6f%n", sumUtils.sum(numbers));
        } else {
            System.out.println("Дополнительное сложение не запрашивалось.");
        }
    }

    /** Задания 6.3 и 8.5: сравнение линий и глубокое клонирование. */
    private void demonstrateLineEqualsAndClone() {
        section("7. Задания 6.3 и 8.5 — equals() и clone() для линии");

        Line original = new Line(
                readInt("Начало — X1: "),
                readInt("Начало — Y1: "),
                readInt("Конец — X2: "),
                readInt("Конец — Y2: ")
        );
        Line sameLine = new Line(original.getStart(), original.getEnd());
        Line reversedLine = new Line(original.getEnd(), original.getStart());

        System.out.println("Исходная линия: " + original);
        System.out.println("Линия с теми же началом и концом: " + sameLine);
        System.out.println("original.equals(sameLine): " + original.equals(sameLine));
        System.out.println("Совпадают hashCode равных линий: " + (original.hashCode() == sameLine.hashCode()));
        System.out.println("Линия с обратным направлением: " + reversedLine);
        System.out.println("original.equals(reversedLine): " + original.equals(reversedLine));

        Line clone = original.clone();
        System.out.println("Клон линии: " + clone);
        clone.setStart(
                readInt("Задайте новую X начальной точки клона: "),
                readInt("Задайте новую Y начальной точки клона: ")
        );
        System.out.println("Измененный клон: " + clone);
        System.out.println("Оригинал после изменения клона (должен остаться прежним): " + original);
    }

    /** Задание 7.3: X и Y принимаются как строки, включая аргументы командной строки. */
    private void demonstratePower(String[] args) {
        section("8. Задание 7.3 — возведение X в степень Y");

        PowerCalculator calculator = new PowerCalculator();
        String x;
        String y;

        if (args != null && args.length == 2) {
            x = args[0];
            y = args[1];
            System.out.println("Получены аргументы командной строки: X = " + x + ", Y = " + y);
        } else {
            if (args != null && args.length > 0) {
                System.out.println("Для расчета степени нужны ровно два аргумента. Введите X и Y с клавиатуры.");
            }
            x = readText("Введите целое число X: ");
            y = readText("Введите целое число Y: ");
        }

        while (true) {
            try {
                double result = calculator.calculate(x, y);
                System.out.println("Результат: " + result);
                System.out.println(calculator);
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Ошибка: X и Y должны быть целыми числами в диапазоне int.");
                x = readText("Повторно введите X: ");
                y = readText("Повторно введите Y: ");
            } catch (IllegalArgumentException exception) {
                System.out.println("Ошибка: " + exception.getMessage());
                x = readText("Повторно введите X: ");
                y = readText("Повторно введите Y: ");
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException exception) {
                System.out.println("Некорректный ввод. Требуется целое число.");
            }
        }
    }

    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Значение должно находиться в диапазоне от " + min + " до " + max + ".");
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim().replace(',', '.');
            try {
                double value = Double.parseDouble(text);
                if (Double.isFinite(value)) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Сообщение об ошибке выводится ниже, после общей проверки.
            }
            System.out.println("Некорректный ввод. Требуется конечное вещественное число.");
        }
    }

    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("Строка не должна быть пустой.");
        }
    }

    private void section(String title) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(title);
        System.out.println("------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Главный демонстратор лабораторной работы № 3, вариант 1";
    }
}

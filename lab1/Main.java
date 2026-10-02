import java.util.Scanner;

public class Main {

    private Scanner in = new Scanner(System.in);

    // ЗАДАНИЕ 1

    public double fraction(double x) {
        return x - (long) x;
    }

    public int sumLastNums(int x) {
        return Math.abs(x % 10) + Math.abs((x / 10) % 10);
    }

    public int charToNum(char x) {
        return x - '0';
    }

    public boolean isPositive(int x) {
        return x > 0;
    }

    public boolean is2Digits(int x) {
        int a = Math.abs(x);
        return a >= 10 && a <= 99;
    }

    // ЗАДАНИЕ 2

    public int abs(int x) {
        if (x < 0) return -x;
        return x;
    }

    public double safeDiv(int x, int y) {
        if (y == 0) {
            System.out.println("Деление на ноль невозможно");
            return 0;
        }
        return (double) x / y;
    }

    public boolean is35(int x) {
        boolean by3 = x % 3 == 0;
        boolean by5 = x % 5 == 0;
        if (by3 && by5) return false;
        return by3 || by5;
    }

    public String makeDecision(int x, int y) {
        if (x < y) return x + " < " + y;
        if (x > y) return x + " > " + y;
        return x + " == " + y;
    }

    public int max3(int x, int y, int z) {
        int max = x;
        if (y > max) max = y;
        if (z > max) max = z;
        return max;
    }

    // ЗАДАНИЕ 3

    public String listNums(int x) {
        String s = "";
        for (int i = 0; i <= x; i++) s += i + " ";
        return s.trim();
    }

    public String reverseListNums(int x) {
        String s = "";
        for (int i = x; i >= 0; i--) s += i + " ";
        return s.trim();
    }

    public String chet(int x) {
        String s = "";
        for (int i = 0; i <= x; i += 2) s += i + " ";
        return s.trim();
    }

    public int pow(int x, int y) {
        int r = 1;
        for (int i = 0; i < y; i++) r *= x;
        return r;
    }

    public int numLen(long x) {
        int c = 0;
        do {
            x /= 10;
            c++;
        } while (x != 0);
        return c;
    }

    // ЗАДАНИЕ 4

    public int findFirst(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == x) return i;
        return -1;
    }

    public int findLast(int[] arr, int x) {
        for (int i = arr.length - 1; i >= 0; i--)
            if (arr[i] == x) return i;
        return -1;
    }

    public int maxAbs(int[] arr) {
        int best = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (Math.abs(arr[i]) > Math.abs(best)) best = arr[i];
        return best;
    }

    public int[] add(int[] arr, int x, int pos) {
        int[] res = new int[arr.length + 1];
        for (int i = 0; i < pos; i++) res[i] = arr[i];
        res[pos] = x;
        for (int i = pos; i < arr.length; i++) res[i + 1] = arr[i];
        return res;
    }

    public int[] add(int[] arr, int[] ins, int pos) {
        int[] res = new int[arr.length + ins.length];
        for (int i = 0; i < pos; i++) res[i] = arr[i];
        for (int i = 0; i < ins.length; i++) res[pos + i] = ins[i];
        for (int i = pos; i < arr.length; i++) res[i + ins.length] = arr[i];
        return res;
    }

    // ВВОД

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (!in.hasNextLine()) {
            System.out.println("\nВвод завершён.");
            System.exit(0);
        }
        return in.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            String s = readLine(prompt);
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            int v = readInt(prompt);
            if (v < min || v > max) {
                System.out.println("Ошибка: число должно быть от " + min + " до " + max + ".");
                continue;
            }
            return v;
        }
    }

    private long readLong(String prompt) {
        while (true) {
            String s = readLine(prompt);
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            String s = readLine(prompt).replace(',', '.');
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private char readDigitChar(String prompt) {
        while (true) {
            String s = readLine(prompt);
            if (s.length() == 1 && s.charAt(0) >= '0' && s.charAt(0) <= '9') {
                return s.charAt(0);
            }
            System.out.println("Ошибка: нужен один символ-цифра от 0 до 9.");
        }
    }

    private int[] readIntArray(String prompt) {
        while (true) {
            String s = readLine(prompt).replace(',', ' ').trim();
            if (s.isEmpty()) {
                System.out.println("Ошибка: массив не должен быть пустым.");
                continue;
            }
            String[] parts = s.split("\\s+");
            int[] res = new int[parts.length];
            boolean ok = true;
            for (int i = 0; i < parts.length; i++) {
                try {
                    res[i] = Integer.parseInt(parts[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: \"" + parts[i] + "\" не число. Введите заново.");
                    ok = false;
                    break;
                }
            }
            if (ok) return res;
        }
    }

    // ВЫВОД

    private String yesNo(boolean b) {
        return b ? "да" : "нет";
    }

    private String arrToStr(int[] a) {
        String s = "[";
        for (int i = 0; i < a.length; i++) {
            s += a[i];
            if (i < a.length - 1) s += ", ";
        }
        return s + "]";
    }

    private void section(String text) {
        System.out.println("\n=== " + text + " ===");
    }

    private void header(String text) {
        System.out.println("\n--- " + text + " ---");
    }

    // ЗАПУСК ЗАДАНИЙ

    private void runTask1() {
        section("ЗАДАНИЕ 1. Методы");

        header("1.1 Дробная часть");
        double a = readDouble("Введите число: ");
        System.out.println("Дробная часть: " + fraction(a));

        header("1.2 Сумма знаков");
        int b;
        while (true) {
            b = readInt("Введите число (не менее двух цифр): ");
            if (Math.abs((long) b) >= 10) break;
            System.out.println("Ошибка: нужно минимум две цифры.");
        }
        System.out.println("Сумма последних двух цифр: " + sumLastNums(b));

        header("1.3 Букву в число");
        char c = readDigitChar("Введите символ-цифру: ");
        System.out.println("Результат: " + charToNum(c));

        header("1.4 Положительное?");
        System.out.println("Результат: " + yesNo(isPositive(readInt("Введите число: "))));

        header("1.5 Двузначное?");
        System.out.println("Результат: " + yesNo(is2Digits(readInt("Введите число: "))));
    }

    private void runTask2() {
        section("ЗАДАНИЕ 2. Условия");

        header("2.1 Модуль числа");
        System.out.println("Модуль: " + abs(readInt("Введите число: ")));

        header("2.2 Безопасное деление");
        int x = readInt("Введите x: ");
        int y = readInt("Введите y: ");
        System.out.println("Результат: " + safeDiv(x, y));

        header("2.3 Тридцать пять");
        System.out.println("Результат: " + yesNo(is35(readInt("Введите число: "))));

        header("2.4 Строка сравнения");
        int p = readInt("Введите x: ");
        int q = readInt("Введите y: ");
        System.out.println(makeDecision(p, q));

        header("2.5 Тройной максимум");
        int m1 = readInt("Введите x: ");
        int m2 = readInt("Введите y: ");
        int m3 = readInt("Введите z: ");
        System.out.println("Максимум: " + max3(m1, m2, m3));
    }

    private void runTask3() {
        section("ЗАДАНИЕ 3. Циклы");

        header("3.1 Числа подряд");
        System.out.println(listNums(readInt("Введите x: ")));

        header("3.2 Числа наоборот");
        System.out.println(reverseListNums(readInt("Введите x: ")));

        header("3.3 Четные числа");
        System.out.println(chet(readInt("Введите x: ")));

        header("3.4 Степень");
        int base = readInt("Введите x: ");
        int exp = readInt("Введите y: ");
        System.out.println("Результат: " + pow(base, exp));

        header("3.5 Длина числа");
        System.out.println("Знаков: " + numLen(readLong("Введите число: ")));
    }

    private void runTask4() {
        section("ЗАДАНИЕ 4. Массивы");
        System.out.println("Массив вводите через пробел, например: 1 2 3 4 5");

        header("4.1 Первое вхождение");
        int[] a1 = readIntArray("Массив: ");
        int x1 = readInt("Искомое число: ");
        System.out.println("Индекс: " + findFirst(a1, x1));

        header("4.2 Последнее вхождение");
        int[] a2 = readIntArray("Массив: ");
        int x2 = readInt("Искомое число: ");
        System.out.println("Индекс: " + findLast(a2, x2));

        header("4.3 Максимум по модулю");
        System.out.println("Результат: " + maxAbs(readIntArray("Массив: ")));

        header("4.4 Вставка одного элемента");
        int[] a4 = readIntArray("Массив: ");
        int x4 = readInt("Число: ");
        int pos4 = readInt("Позиция (0.." + a4.length + "): ", 0, a4.length);
        System.out.println("Результат: " + arrToStr(add(a4, x4, pos4)));

        header("4.5 Вставка массива");
        int[] a5 = readIntArray("Основной массив: ");
        int[] ins = readIntArray("Вставляемый массив: ");
        int pos5 = readInt("Позиция (0.." + a5.length + "): ", 0, a5.length);
        System.out.println("Результат: " + arrToStr(add(a5, ins, pos5)));
    }

    public static void main(String[] args) {
        Main lab = new Main();

        lab.runTask1();
        lab.runTask2();
        lab.runTask3();
        lab.runTask4();
    }
}
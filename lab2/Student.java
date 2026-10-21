import java.util.Arrays;

public class Student {
    private String name;
    private int[] grades;

    public Student(String name, int[] grades) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Имя не может быть пустым");
        if (grades == null) throw new IllegalArgumentException("Массив оценок не может быть null");
        this.name = name;
        this.grades = grades;
    }

    public String getName() { return name; }
    public int[] getGrades() { return grades; }

    public void setGrade(int index, int grade) {
        if (index < 0 || index >= grades.length) throw new IndexOutOfBoundsException("Нет оценки с таким номером");
        if (grade < 2 || grade > 5) throw new IllegalArgumentException("Оценка должна быть от 2 до 5");
        grades[index] = grade;
    }

    @Override
    public String toString() {
        return name + ": " + Arrays.toString(grades);
    }
}

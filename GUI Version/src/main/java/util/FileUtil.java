package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Course;
import model.Enrollment;
import model.Student;

public class FileUtil {
    public static <T> void saveData(String fileName, List<T> data) {
        if (data == null || data.isEmpty()) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            if (data.get(0) instanceof Student) {
                writer.println("studentId,firstName,lastName,email,phoneNumber");
                for (T item : data) {
                    Student s = (Student) item;
                    writer.println(String.join(",",
                            escapeCsv(s.getStudentId()),
                            escapeCsv(s.getFirstName()),
                            escapeCsv(s.getLastName()),
                            escapeCsv(s.getEmail()),
                            escapeCsv(s.getPhoneNumber())));
                }
            } else if (data.get(0) instanceof Course) {
                writer.println("courseCode,courseName,credits,instructor");
                for (T item : data) {
                    Course c = (Course) item;
                    writer.println(String.join(",",
                            escapeCsv(c.getCourseCode()),
                            escapeCsv(c.getCourseName()),
                            String.valueOf(c.getCredits()),
                            escapeCsv(c.getInstructor())));
                }
            } else if (data.get(0) instanceof Enrollment) {
                writer.println("enrollmentId,studentId,courseCode,enrollmentDate");
                for (T item : data) {
                    Enrollment e = (Enrollment) item;
                    writer.println(String.join(",",
                            escapeCsv(e.getEnrollmentId()),
                            escapeCsv(e.getStudentId()),
                            escapeCsv(e.getCourseCode()),
                            String.valueOf(e.getEnrollmentDate().getTime())));
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadData(String fileName) {
        List<T> data = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null) return data;

            while ((line = reader.readLine()) != null) {
                String[] values = parseCsvLine(line);
                if (values.length == 0) continue;

                if (fileName.contains("students.txt") && values.length == 5) {
                    Student student = new Student(
                            unescapeCsv(values[0]),
                            unescapeCsv(values[1]),
                            unescapeCsv(values[2]),
                            unescapeCsv(values[3]),
                            unescapeCsv(values[4]));
                    data.add((T) student);
                } else if (fileName.contains("courses.txt") && values.length == 4) {
                    Course course = new Course(
                            unescapeCsv(values[0]),
                            unescapeCsv(values[1]),
                            Integer.parseInt(values[2]),
                            unescapeCsv(values[3]));
                    data.add((T) course);
                } else if (fileName.contains("enrollments.txt") && values.length == 4) {
                    Enrollment enrollment = new Enrollment(
                            unescapeCsv(values[0]),
                            unescapeCsv(values[1]),
                            unescapeCsv(values[2]),
                            new Date(Long.parseLong(values[3])));
                    data.add((T) enrollment);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return data;
    }

    private static String escapeCsv(String input) {
        if (input == null) return "";
        return "\"" + input.replace("\"", "\"\"") + "\"";
    }

    private static String unescapeCsv(String input) {
        if (input == null || input.isEmpty()) return "";
        if (input.startsWith("\"") && input.endsWith("\"")) {
            return input.substring(1, input.length() - 1).replace("\"\"", "\"");
        }
        return input;
    }

    private static String[] parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder buffer = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(buffer.toString());
                buffer.setLength(0);
            } else {
                buffer.append(c);
            }
        }
        values.add(buffer.toString());
        return values.toArray(new String[0]);
    }
}

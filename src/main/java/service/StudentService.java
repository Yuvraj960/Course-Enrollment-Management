package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Student;
import util.FileUtil;
import util.InputValidator;

public class StudentService {
    private Map<String, Student> students;
    private static final String FILE_NAME = "src/main/resources/students.txt";

    public StudentService() {
        this.students = new HashMap<>();
        loadStudents();
    }

    public void addStudent(Student student) {
        if (InputValidator.validateStudent(student)) {
            students.put(student.getStudentId(), student);
            saveStudents();
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Invalid student data. Please check all fields.");
        }
    }

    public void updateStudent(String studentId, Student updatedStudent) {
        if (students.containsKey(studentId)) {
            students.put(studentId, updatedStudent);
            saveStudents();
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    public void deleteStudent(String studentId) {
        if (students.remove(studentId) != null) {
            saveStudents();
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Student> searchStudents(String keyword) {
        List<Student> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Student student : students.values()) {
            if (student.getStudentId().toLowerCase().contains(lowerKeyword) ||
                    student.getFirstName().toLowerCase().contains(lowerKeyword) ||
                    student.getLastName().toLowerCase().contains(lowerKeyword) ||
                    student.getEmail().toLowerCase().contains(lowerKeyword)) {
                result.add(student);
            }
        }
        return result;
    }

    public void sortStudents(List<Student> studentList, String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "id":
                Collections.sort(studentList, Comparator.comparing(Student::getStudentId));
                break;
            case "firstname":
                Collections.sort(studentList, Comparator.comparing(Student::getFirstName));
                break;
            case "lastname":
                Collections.sort(studentList, Comparator.comparing(Student::getLastName));
                break;
            default:
                System.out.println("Invalid sort option. Sorting by student ID.");
                Collections.sort(studentList, Comparator.comparing(Student::getStudentId));
        }
    }

    private void loadStudents() {
        List<Student> loadedStudents = FileUtil.loadData(FILE_NAME);
        for (Student student : loadedStudents) {
            students.put(student.getStudentId(), student);
        }
    }

    private void saveStudents() {
        FileUtil.saveData(FILE_NAME, new ArrayList<>(students.values()));
    }
}
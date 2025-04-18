package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Enrollment;
import util.FileUtil;

public class EnrollmentService {
    private Map<String, Enrollment> enrollments;
    private static final String FILE_NAME = "src/main/resources/enrollments.txt";

    public EnrollmentService() {
        this.enrollments = new HashMap<>();
        loadEnrollments();
    }

    public void enrollStudent(String studentId, String courseCode) {
        String enrollmentId = generateEnrollmentId(studentId, courseCode);
        if (!enrollments.containsKey(enrollmentId)) {
            Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseCode, new Date());
            enrollments.put(enrollmentId, enrollment);
            saveEnrollments();
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Student is already enrolled in this course.");
        }
    }

    public void dropEnrollment(String enrollmentId) {
        if (enrollments.remove(enrollmentId) != null) {
            saveEnrollments();
            System.out.println("Enrollment dropped successfully.");
        } else {
            System.out.println("Enrollment not found with ID: " + enrollmentId);
        }
    }

    public Enrollment getEnrollment(String enrollmentId) {
        return enrollments.get(enrollmentId);
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments.values());
    }

    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments.values()) {
            if (enrollment.getStudentId().equals(studentId)) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public List<Enrollment> getEnrollmentsByCourse(String courseCode) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments.values()) {
            if (enrollment.getCourseCode().equals(courseCode)) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public void sortEnrollments(List<Enrollment> enrollmentList, String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "id":
                Collections.sort(enrollmentList, Comparator.comparing(Enrollment::getEnrollmentId));
                break;
            case "student":
                Collections.sort(enrollmentList, Comparator.comparing(Enrollment::getStudentId));
                break;
            case "course":
                Collections.sort(enrollmentList, Comparator.comparing(Enrollment::getCourseCode));
                break;
            case "date":
                Collections.sort(enrollmentList, Comparator.comparing(Enrollment::getEnrollmentDate));
                break;
            default:
                System.out.println("Invalid sort option. Sorting by enrollment ID.");
                Collections.sort(enrollmentList, Comparator.comparing(Enrollment::getEnrollmentId));
        }
    }

    private String generateEnrollmentId(String studentId, String courseCode) {
        return studentId + "-" + courseCode;
    }

    private void loadEnrollments() {
        List<Enrollment> loadedEnrollments = FileUtil.loadData(FILE_NAME);
        for (Enrollment enrollment : loadedEnrollments) {
            enrollments.put(enrollment.getEnrollmentId(), enrollment);
        }
    }

    private void saveEnrollments() {
        FileUtil.saveData(FILE_NAME, new ArrayList<>(enrollments.values()));
    }
}
package util;

import model.Course;
import model.Student;

public class InputValidator {
    public static int validateStudent(Student student) {
        if (student == null) return 1;
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty() || !student.getFirstName().matches("^[a-zA-Z]+$")) return 2;
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty() || !student.getLastName().matches("^[a-zA-Z]+$")) return 3;
        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) return 4;
        if (student.getEmail() == null || !student.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) return 5;
        if (student.getPhoneNumber() == null || !student.getPhoneNumber().matches("^\\+?[0-9]{10,15}$")) return 6;
        return 7;
    }

    public static boolean validateCourse(Course course) {
        if (course == null) return false;
        if (course.getCourseCode() == null || course.getCourseCode().trim().isEmpty()) return false;
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) return false;
        if (course.getCredits() <= 0) return false;
        if (course.getInstructor() == null || course.getInstructor().trim().isEmpty()) return false;
        return true;
    }
}

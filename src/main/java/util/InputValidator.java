package util;

import model.Course;
import model.Student;

public class InputValidator {
    public static boolean validateStudent(Student student) {
        if (student == null) return false;
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) return false;
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) return false;
        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) return false;
        if (student.getEmail() == null || !student.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) return false;
        if (student.getPhoneNumber() == null || !student.getPhoneNumber().matches("^\\+?[0-9]{10,15}$")) return false;
        return true;
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

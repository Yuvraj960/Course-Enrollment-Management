package model;

import java.io.Serializable;
import java.util.Date;

public class Enrollment implements Serializable {
    private String enrollmentId;
    private String studentId;
    private String courseCode;
    private Date enrollmentDate;

    public Enrollment(String enrollmentId, String studentId, String courseCode, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and setters
    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    @Override
    public String toString() {
        return String.format("%-15s %-10s %-10s %-20s",
                enrollmentId, studentId, courseCode, enrollmentDate);
    }
}
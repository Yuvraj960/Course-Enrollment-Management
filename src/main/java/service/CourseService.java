package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Course;
import util.FileUtil;
import util.InputValidator;

public class CourseService {
    private Map<String, Course> courses;
    private static final String FILE_NAME = "src/main/resources/courses.txt";

    public CourseService() {
        this.courses = new HashMap<>();
        loadCourses();
    }

    public void addCourse(Course course) {
        if (InputValidator.validateCourse(course)) {
            courses.put(course.getCourseCode(), course);
            saveCourses();
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Invalid course data. Please check all fields.");
        }
    }

    public void updateCourse(String courseCode, Course updatedCourse) {
        if (courses.containsKey(courseCode)) {
            courses.put(courseCode, updatedCourse);
            saveCourses();
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found with code: " + courseCode);
        }
    }

    public void deleteCourse(String courseCode) {
        if (courses.remove(courseCode) != null) {
            saveCourses();
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course not found with code: " + courseCode);
        }
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public List<Course> searchCourses(String keyword) {
        List<Course> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Course course : courses.values()) {
            if (course.getCourseCode().toLowerCase().contains(lowerKeyword) ||
                    course.getCourseName().toLowerCase().contains(lowerKeyword) ||
                    course.getInstructor().toLowerCase().contains(lowerKeyword)) {
                result.add(course);
            }
        }
        return result;
    }

    public void sortCourses(List<Course> courseList, String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "code":
                Collections.sort(courseList, Comparator.comparing(Course::getCourseCode));
                break;
            case "name":
                Collections.sort(courseList, Comparator.comparing(Course::getCourseName));
                break;
            case "credits":
                Collections.sort(courseList, Comparator.comparingInt(Course::getCredits));
                break;
            default:
                System.out.println("Invalid sort option. Sorting by course code.");
                Collections.sort(courseList, Comparator.comparing(Course::getCourseCode));
        }
    }

    private void loadCourses() {
        List<Course> loadedCourses = FileUtil.loadData(FILE_NAME);
        for (Course course : loadedCourses) {
            courses.put(course.getCourseCode(), course);
        }
    }

    private void saveCourses() {
        FileUtil.saveData(FILE_NAME, new ArrayList<>(courses.values()));
    }
}
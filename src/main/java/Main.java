import java.util.List;
import java.util.Scanner;
import model.Course;
import model.Enrollment;
import model.Student;
import service.CourseService;
import service.EnrollmentService;
import service.StudentService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Enrollments");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageEnrollments();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void manageStudents() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== Student Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Search Students");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    searchStudents();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n===== Add New Student =====");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Student student = new Student(studentId, firstName, lastName, email, phoneNumber);
        studentService.addStudent(student);
    }

    private static void updateStudent() {
        System.out.println("\n===== Update Student =====");
        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine();

        Student existingStudent = studentService.getStudent(studentId);
        if (existingStudent == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        System.out.println("Current Student Details:");
        displayStudentHeader();
        System.out.println(existingStudent);

        System.out.print("Enter new First Name (leave blank to keep current): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) existingStudent.setFirstName(firstName);

        System.out.print("Enter new Last Name (leave blank to keep current): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) existingStudent.setLastName(lastName);

        System.out.print("Enter new Email (leave blank to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) existingStudent.setEmail(email);

        System.out.print("Enter new Phone Number (leave blank to keep current): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) existingStudent.setPhoneNumber(phoneNumber);

        studentService.updateStudent(studentId, existingStudent);
    }

    private static void deleteStudent() {
        System.out.println("\n===== Delete Student =====");
        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine();

        studentService.deleteStudent(studentId);
    }

    private static void viewAllStudents() {
        System.out.println("\n===== All Students =====");
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.print("Sort by (id/firstname/lastname): ");
        String sortBy = scanner.nextLine().toLowerCase();
        studentService.sortStudents(students, sortBy);

        displayStudentHeader();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudents() {
        System.out.println("\n===== Search Students =====");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Student> results = studentService.searchStudents(keyword);

        if (results.isEmpty()) {
            System.out.println("No students found matching your search.");
            return;
        }

        System.out.println("\nSearch Results:");
        displayStudentHeader();
        for (Student student : results) {
            System.out.println(student);
        }
    }

    private static void displayStudentHeader() {
        System.out.printf("%-10s %-15s %-15s %-25s %-15s%n",
                "ID", "First Name", "Last Name", "Email", "Phone");
    }

    private static void manageCourses() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== Course Management =====");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. View All Courses");
            System.out.println("5. Search Courses");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    updateCourse();
                    break;
                case 3:
                    deleteCourse();
                    break;
                case 4:
                    viewAllCourses();
                    break;
                case 5:
                    searchCourses();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCourse() {
        System.out.println("\n===== Add New Course =====");
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter Credits: ");
        int credits = getIntInput(1, 10);

        System.out.print("Enter Instructor Name: ");
        String instructor = scanner.nextLine();

        Course course = new Course(courseCode, courseName, credits, instructor);
        courseService.addCourse(course);
    }

    private static void updateCourse() {
        System.out.println("\n===== Update Course =====");
        System.out.print("Enter Course Code to update: ");
        String courseCode = scanner.nextLine();

        Course existingCourse = courseService.getCourse(courseCode);
        if (existingCourse == null) {
            System.out.println("Course not found with code: " + courseCode);
            return;
        }

        System.out.println("Current Course Details:");
        displayCourseHeader();
        System.out.println(existingCourse);

        System.out.print("Enter new Course Name (leave blank to keep current): ");
        String courseName = scanner.nextLine();
        if (!courseName.isEmpty()) existingCourse.setCourseName(courseName);

        System.out.print("Enter new Credits (0 to keep current): ");
        int credits = getIntInput(0, 10);
        if (credits != 0) existingCourse.setCredits(credits);

        System.out.print("Enter new Instructor (leave blank to keep current): ");
        String instructor = scanner.nextLine();
        if (!instructor.isEmpty()) existingCourse.setInstructor(instructor);

        courseService.updateCourse(courseCode, existingCourse);
    }

    private static void deleteCourse() {
        System.out.println("\n===== Delete Course =====");
        System.out.print("Enter Course Code to delete: ");
        String courseCode = scanner.nextLine();

        courseService.deleteCourse(courseCode);
    }

    private static void viewAllCourses() {
        System.out.println("\n===== All Courses =====");
        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.print("Sort by (code/name/credits): ");
        String sortBy = scanner.nextLine().toLowerCase();
        courseService.sortCourses(courses, sortBy);

        displayCourseHeader();
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void searchCourses() {
        System.out.println("\n===== Search Courses =====");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Course> results = courseService.searchCourses(keyword);

        if (results.isEmpty()) {
            System.out.println("No courses found matching your search.");
            return;
        }

        System.out.println("\nSearch Results:");
        displayCourseHeader();
        for (Course course : results) {
            System.out.println(course);
        }
    }

    private static void displayCourseHeader() {
        System.out.printf("%-10s %-25s %-8s %-20s%n",
                "Code", "Name", "Credits", "Instructor");
    }

    private static void manageEnrollments() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== Enrollment Management =====");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Drop Enrollment");
            System.out.println("3. View All Enrollments");
            System.out.println("4. View Enrollments by Student");
            System.out.println("5. View Enrollments by Course");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    dropEnrollment();
                    break;
                case 3:
                    viewAllEnrollments();
                    break;
                case 4:
                    viewEnrollmentsByStudent();
                    break;
                case 5:
                    viewEnrollmentsByCourse();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enrollStudent() {
        System.out.println("\n===== Enroll Student =====");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (studentService.getStudent(studentId) == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        if (courseService.getCourse(courseCode) == null) {
            System.out.println("Course not found with code: " + courseCode);
            return;
        }

        enrollmentService.enrollStudent(studentId, courseCode);
    }

    private static void dropEnrollment() {
        System.out.println("\n===== Drop Enrollment =====");
        System.out.print("Enter Enrollment ID: ");
        String enrollmentId = scanner.nextLine();

        enrollmentService.dropEnrollment(enrollmentId);
    }

    private static void viewAllEnrollments() {
        System.out.println("\n===== All Enrollments =====");
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        System.out.print("Sort by (id/student/course/date): ");
        String sortBy = scanner.nextLine().toLowerCase();
        enrollmentService.sortEnrollments(enrollments, sortBy);

        displayEnrollmentHeader();
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private static void viewEnrollmentsByStudent() {
        System.out.println("\n===== Enrollments by Student =====");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (studentService.getStudent(studentId) == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }

        System.out.println("\nEnrollments for Student ID: " + studentId);
        displayEnrollmentHeader();
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private static void viewEnrollmentsByCourse() {
        System.out.println("\n===== Enrollments by Course =====");
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        if (courseService.getCourse(courseCode) == null) {
            System.out.println("Course not found with code: " + courseCode);
            return;
        }

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseCode);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this course.");
            return;
        }

        System.out.println("\nEnrollments for Course Code: " + courseCode);
        displayEnrollmentHeader();
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private static void displayEnrollmentHeader() {
        System.out.printf("%-15s %-10s %-10s %-20s%n",
                "Enrollment ID", "Student", "Course", "Enrollment Date");
    }

    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
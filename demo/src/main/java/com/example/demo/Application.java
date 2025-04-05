package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.User.*;
import com.example.demo.subclasses.*; // Import User classes (Student, Teacher, Admin)

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
public CommandLineRunner initDatabase() {
    return args -> {
        try {
            // Creating new users with unique names and emails
            User student1 = new Student(null, "Alice Johnson", "alice.johnson", "alice.johnson@student.com", "password1001");
            User student2 = new Student(null, "Bob Miller", "bob.miller", "bob.miller@student.com", "password1002");
            User teacher = new Teacher(null, "Catherine Smith", "catherine.smith", "catherine.smith@teacher.com", "password1003");
            User admin = new Admin(null, "David Brown", "david.brown", "david.brown@admin.com", "password1004");

            // Save users to the repository
            userRepository.create(student1);
            userRepository.create(student2);
            userRepository.create(teacher);
            userRepository.create(admin);

            System.out.println("✅ Users added to the database.");
        } catch (Exception e) {
            System.err.println("❌ Error inserting users: " + e.getMessage());
        }
    };
}
    @Bean
    public CommandLineRunner testDatabase() {
        return args -> {
            try {
                // Fetch Students
                System.out.println("📚 Retrieving Students:");
                List<Student> students = userRepository.findStudents();
                students.forEach(s -> System.out.println("Student: " + s.getUsername() + ", Email: " + s.getEmail()));

                // Fetch Teachers
                System.out.println("\n🎓 Retrieving Teachers:");
                List<Teacher> teachers = userRepository.findTeachers();
                teachers.forEach(t -> System.out.println("Teacher: " + t.getUsername() + ", Email: " + t.getEmail()));

                // Fetch Admins
                System.out.println("\n🛠️ Retrieving Admins:");
                List<Admin> admins = userRepository.findAdmins();
                admins.forEach(a -> System.out.println("Admin: " + a.getUsername() + ", Email: " + a.getEmail()));

            } catch (Exception e) {
                System.err.println("❌ Error retrieving users: " + e.getMessage());
            }
        };
    }
}

package com.example.demo;

import com.example.demo.Grade.Grade;
import com.example.demo.Grade.GradeRepository;
import com.example.demo.Group.*;
import com.example.demo.Level.*;
import com.example.demo.Subject.*;
import com.example.demo.WorkReturn.WorkReturn;
import com.example.demo.WorkReturn.WorkReturnRepository;
import com.example.demo.subclasses.*;
import com.example.demo.Assignment.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@SpringBootApplication
@EntityScan("com.example.demo")
@EnableJpaRepositories("com.example.demo")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
   @Bean
CommandLineRunner testEverything(StudentRepository studentRepo,
                                  TeacherRepository teacherRepo,
                                  GroupRepository groupRepo,
                                  LevelRepository levelRepo,
                                  SubjectRepository subjectRepo,
                                  AssignmentRepository assignmentRepo,
                                  GradeRepository gradeRepo,
                                  WorkReturnRepository workReturnRepo,
                                  AdminRepository adminRepo) {
    return args -> {
        Level level = new Level("First Level");
        levelRepo.save(level);

        Subject math = new Subject("Math", level);
        subjectRepo.save(math);

        Teacher teacher = new Teacher("teacher1", "teacher1@example.com", "pass123");
        teacher.setSubjects(List.of(math));
        teacherRepo.save(teacher);

        // Create Group and add Teacher to the Group using addTeacher method
        Group group = new Group("Group A", level);
        group.addTeacher(teacher);  // Add teacher to the group
        groupRepo.save(group);

        Student student = new Student("student1", "student1@example.com", "pass456", "12th Grade", group, level);
        studentRepo.save(student);

        Admin admin = new Admin("admin1", "admin1@example.com", "adminpass");
        adminRepo.save(admin);

        Assignment assignment = new Assignment("Math Homework", "Solve equations", group, subjectRepo.findById(math.getId()).orElse(null));
        assignmentRepo.save(assignment);

        Grade grade = new Grade("A+", student, assignment);
        gradeRepo.save(grade);

        WorkReturn workReturn = new WorkReturn("student1's submission", student, assignment);
        workReturnRepo.save(workReturn);

        System.out.println("✅ All entities saved successfully!");
    };
}
      
}

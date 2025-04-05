package com.example.demo.Assignment;

import java.time.LocalDateTime;

public record Assignment(
        Integer id,
        String title,
        String description,
        LocalDateTime dueDate,
        Integer teacherId // ID of the teacher who uploaded the assignment
) {
}

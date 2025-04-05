package com.example.demo.Assignment;

public class AssignmentNotFoundException extends RuntimeException {
    public AssignmentNotFoundException() {
        super("Assignment Not Found");
    }
}

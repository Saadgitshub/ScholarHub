package com.example.demo.DueWorks;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class DueWork {
    private int id;

    @NotEmpty(message = "At least one file path is required.")
    private List<String> filePaths;

    public DueWork() {}

    public DueWork(int id, List<String> filePaths) {
        this.id = id;
        this.filePaths = filePaths;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }
}

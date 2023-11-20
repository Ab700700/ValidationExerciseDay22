package com.example.question2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "ID should not be empty")
    @Size(min = 2, message = "ID must have 2 characters at least")
    private String ID;
    @NotEmpty(message = "title should not be empty")
    @Size(min = 8, message = "title must have 8 characters at least")
    private String title;
    @NotEmpty(message = "description should not be empty")
    @Size(min = 15, message = "description must have 15 characters at least")
    private String description;
    @NotEmpty(message = "status should not be empty")
    @Pattern(regexp = "Not started|Progress|Completed")
    private String status;
    @NotEmpty(message = "company name should not be empty")
    @Size(min = 6,message = "company name must have 6 characters at least")
    private String companyName;
}

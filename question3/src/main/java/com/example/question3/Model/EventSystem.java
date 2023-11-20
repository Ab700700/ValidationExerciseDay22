package com.example.question3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class EventSystem {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 2, message = "id two characters at least")
    private String id;
    @NotEmpty(message = "description should not be empty")
    @Size(min = 2,message = "description two characters at least")
    private String description;
    @NotNull(message = "capacity should not be null")
    @Min(value = 25, message = "capacity at least should be 25")
    private int capacity;
    @NotNull(message = "startDate should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "date must be in present or future")
    private LocalDateTime startDate;
    @NotNull(message = "endDate should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "date must be in present or future")
    private LocalDateTime endDate;
}

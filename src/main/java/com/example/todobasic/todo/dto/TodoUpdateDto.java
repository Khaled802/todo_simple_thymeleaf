package com.example.todobasic.todo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoUpdateDto {
    @NotEmpty
    @Size(max = 21, message = "title must be less than 20 letters")
    private String title;
    
    @NotEmpty
    @Size(min = 11, message = "description must be more than 10 letters")
    private String description;

    @NotNull
    private Boolean done;

    @NotNull
    private LocalDate end;
}

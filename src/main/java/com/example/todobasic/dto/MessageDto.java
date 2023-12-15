package com.example.todobasic.dto;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MessageDto {
    private String message;
    private List<String> messageList;
}

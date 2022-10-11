package com.example.Sprint1MGN.controller.dto.response;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class Response {
    private String message;
}

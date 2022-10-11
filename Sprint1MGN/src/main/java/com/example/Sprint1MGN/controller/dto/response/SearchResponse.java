package com.example.Sprint1MGN.controller.dto.response;

import com.example.Sprint1MGN.model.entity.Mgni;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponse {
    private String message;
    private List<Mgni> mgniList;
}

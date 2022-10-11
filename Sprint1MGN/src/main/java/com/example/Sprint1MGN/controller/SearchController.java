package com.example.Sprint1MGN.controller;

import com.example.Sprint1MGN.controller.dto.request.SearchRequest;
import com.example.Sprint1MGN.controller.dto.response.SearchResponse;
import com.example.Sprint1MGN.service.tool.searchTool.searchable.MgniSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

//@Autowired
//private UserSearchable userSearchable;

@RestController
@RequestMapping(path = "/api/v1/search")
@Validated
public class SearchController {

    @Autowired
    MgniSearchable mgniSearchable;

    @PostMapping(path = "/mgni", consumes = {MediaType.APPLICATION_XML_VALUE}) //, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    //XML, Json通用
    public ResponseEntity<SearchResponse> SearchMgni(@NotNull(message = "must enter pageNum.") @RequestParam Integer pageNum,
                                                     @NotNull(message = "must enter pageSize.") @RequestParam Integer pageSize,
                                                     @Valid @RequestBody SearchRequest request) {
        SearchResponse response;
//        try {
            response = mgniSearchable.search(pageNum, pageSize, request);
            return ResponseEntity.ok().body(response);
//        } catch (Exception e) {
//            response = new SearchResponse().builder().message(e.getMessage()).build();
//            return ResponseEntity.ok().body(response);
//        }
    }

}

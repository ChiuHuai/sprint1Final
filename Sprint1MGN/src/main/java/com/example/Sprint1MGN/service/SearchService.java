package com.example.Sprint1MGN.service;

import com.example.Sprint1MGN.controller.dto.request.SearchRequest;
import com.example.Sprint1MGN.controller.dto.response.SearchResponse;
import com.example.Sprint1MGN.model.MgniRepository;
import com.example.Sprint1MGN.model.entity.Mgni;
import com.example.Sprint1MGN.service.tool.searchTool.MgniSpecs;
import com.example.Sprint1MGN.service.tool.searchTool.searchable.MgniSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements MgniSearchable {

    @Autowired
    private MgniRepository mgniRepository;

    @Override
    public SearchResponse search(Integer pageNum, Integer pageSize, SearchRequest request) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize); //原本第一頁pagenum = 0
        Specification<Mgni> spec = MgniSpecs.getSpecs(request);
        List<Mgni> mgniList = mgniRepository.findAll(spec, pageable).toList();
        if (mgniList.size() == 0) {
            return new SearchResponse().builder().mgniList(mgniList).message("no data").build();
        }
        return new SearchResponse().builder().mgniList(mgniList).message("OK").build();
    }
}

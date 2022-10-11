package com.example.Sprint1MGN.controller;

import com.example.Sprint1MGN.controller.dto.request.DepositRequest;
import com.example.Sprint1MGN.controller.dto.request.UpdateRequest;
import com.example.Sprint1MGN.controller.dto.response.Response;
import com.example.Sprint1MGN.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(path = "/api/v1")
@Validated
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Response> deposit(@Valid @RequestBody DepositRequest request) {
        Response response;
        try {
            response = transactionService.deposit(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response = new Response().builder().message(e.getMessage()).build();
            return ResponseEntity.ok().body(response);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> deleteMGN(@NotBlank(message = "must enter id.") @RequestParam String id) { //validated
        Response response;
        try {
            response = transactionService.deleteMGN(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response = new Response().builder().message(e.getMessage()).build();
            return ResponseEntity.ok().body(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateMGN(@Valid @RequestBody UpdateRequest request) {
        Response response;
        try {
            response = transactionService.updateMGN(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response = new Response().builder().message("from try catch").build();
            return ResponseEntity.ok().body(response);
        }
    }

}

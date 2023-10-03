package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.response.DataRegisterResponse;
import com.alura.latam.forum.domain.response.DataUpdateResponse;
import com.alura.latam.forum.domain.response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "bearer-key")
public class controllerAuthenticationProcess {

    @Autowired
    private ResponseService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Next, you need to register a new response",
            tags = {"response", "post"}
    )
    public ResponseEntity registerResponse(@RequestBody @Valid DataRegisterResponse data) {
        var response = service.registerResponse(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Please, update the information in the answer.",
            tags = {"response", "put"}
    )
    public ResponseEntity updateResponse(@RequestBody @Valid DataUpdateResponse data) {
        var response = service.updateResponse(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "please, delete a reply",
            tags = {"response", "delete"}
    )
    public ResponseEntity deleteResponse(@PathVariable @Min(1) Long id) {
        service.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}

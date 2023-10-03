package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.course.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class ControllerCourse {

    @Autowired
    private CourseService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "The administrator is the only one who can register a new course.",
            tags = {"course", "post"}
    )
    public ResponseEntity registerCourse(@RequestBody @Valid DataRegisterCourse data) {
        var response = service.registerCourse(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "The administrator is the only one who can update data for a new course.",
            tags = {"course", "put"}
    )
    public ResponseEntity updateCourse(@RequestBody @Valid DataUpdateCourse data) {
        var course = service.updateCourse(data);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "The administrator is the only one who can delete data from a new course.",
            tags = {"course", "delete"}
    )
    public ResponseEntity deleteCourse(@PathVariable @Min(1) Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

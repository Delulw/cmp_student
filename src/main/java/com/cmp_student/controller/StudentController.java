package com.cmp_student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmp_student.dto.StudentDto;
import com.cmp_student.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1.- Agregar anotación @Validated
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody StudentDto playerDto) {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}

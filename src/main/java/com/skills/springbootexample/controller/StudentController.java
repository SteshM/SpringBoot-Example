package com.skills.springbootexample.controller;


import com.skills.springbootexample.DTO.Student;

import com.skills.springbootexample.entities.StudentEntity;
import com.skills.springbootexample.repositories.StudentRepository;
import com.skills.springbootexample.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "api/v1")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentService studentService;
    @Autowired
    public StudentRepository studentRepository;

    @PostMapping("/student")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {

        StudentEntity record= studentService.createStudent(student);

        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }


     //available students
    @GetMapping("/students")
    public ResponseEntity<?> availableStudents(){
        logger.info("About fetching all students");
        List<StudentEntity> allData = studentService.getStudents();
        logger.info("Fetched details of {} students", allData.size());
        return  new ResponseEntity<>(allData, HttpStatus.OK);

    }

    //get one student
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        logger.info("about to fetch details of a single student with id {} ",id);
        StudentEntity singleStudent = studentService.getStudent(id);
        logger.info("Student fetched:{}", singleStudent);
        return new ResponseEntity<>(singleStudent , HttpStatus.OK);
    }

    //update a record
    @PutMapping("/student/{id}")
    public ResponseEntity<?> replaceStudent(@PathVariable Long id,@RequestBody Student student){
        StudentEntity singleStudent = studentService.updateStudent(id , student);

        return new ResponseEntity<>(singleStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        studentService.deleteById(id);
    }
}



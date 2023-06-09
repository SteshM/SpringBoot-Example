package com.skills.springbootexample.service;

import com.skills.springbootexample.DTO.Student;
import com.skills.springbootexample.entities.StudentEntity;
import com.skills.springbootexample.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;



    public int calculateAge(LocalDate dob) {

        LocalDate now = LocalDate.now();
        Period age = Period.between(dob, now);
        return age.getYears();
    }

    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudent(Long id) {
        return studentRepository.getStudentById(id);
    }


    public StudentEntity findStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        System.out.println("data" + studentEntity);
        return null;
    }
    public StudentEntity updateStudent(Long id, Student student) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        studentEntity.setName(student.getName());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setDob(student.getDob());

        int age = calculateAge(student.getDob());
        studentEntity.setAge(age);

        return studentRepository.save(studentEntity);
    }


    public StudentEntity createStudent(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(student.getName());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setDob(student.getDob());

        int age = calculateAge(student.getDob());
        studentEntity.setAge(age);

        return  studentRepository.save(studentEntity);

    }
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

}


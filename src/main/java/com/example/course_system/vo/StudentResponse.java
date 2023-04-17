package com.example.course_system.vo;

import com.example.course_system.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    private String message;
    private Student student;

//    Constructor
    public StudentResponse() {}
    public StudentResponse(String message) {
        this.message = message;
    }
    public StudentResponse(String message, Student student) {
        this.message = message;
        this.student = student;
    }

//    Getter
    public String getMessage() {
        return message;
    }
    public Student getStudent() {
        return student;
    }

//    Setter
    public void setMessage(String message) {
        this.message = message;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}

package com.example.course_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentRequest {
    @JsonProperty("student_id")
    private String studentID;
    @JsonProperty("student_name")
    private String studentName;

//    Getter
    public String getStudentID() {
        return studentID;
    }
    public String getStudentName() {
        return studentName;
    }

//    Setter
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}

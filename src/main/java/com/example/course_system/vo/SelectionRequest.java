package com.example.course_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SelectionRequest {
    @JsonProperty("student_id")
    private String studentID;
    @JsonProperty("course_id")
    private String courseID;

    public SelectionRequest() {
    }

    public SelectionRequest(String studentID) {
        this.studentID = studentID;
    }

    public SelectionRequest(String studentID, String courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    //    Getter
    public String getStudentID() {
        return studentID;
    }
    public String getCourseID() {
        return courseID;
    }

//    Setter
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}

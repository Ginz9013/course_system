package com.example.course_system.vo;

import com.example.course_system.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {
    private String message;
    private Course course;

//    Constructor
    public CourseResponse() {}

    public CourseResponse(String message) {
        this.message = message;
    }

    public CourseResponse(String message,Course course) {
        this.message = message;
        this.course = course;
    }

//    Getter
    public String getMessage() {
        return message;
    }

    public Course getCourse() {
        return course;
    }

//    Setter
    public void setMessage(String message) {
        this.message = message;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

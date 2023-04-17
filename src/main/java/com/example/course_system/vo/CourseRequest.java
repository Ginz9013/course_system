package com.example.course_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseRequest {
    @JsonProperty("course_id")
    private String courseID;
    @JsonProperty("course_name")
    private String courseName;
    private Integer day;
    @JsonProperty("start_time")
    private Integer startTime;
    @JsonProperty("end_time")
    private Integer endTime;
    private Integer credit;

//    Getter
    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public Integer getCredit() {
        return credit;
    }

//    Setter
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}

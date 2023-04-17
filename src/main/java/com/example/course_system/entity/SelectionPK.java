package com.example.course_system.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

public class SelectionPK implements Serializable{
    private String studentID;
    private String courseID;


//    Constructor
    public SelectionPK() {
    }
    public SelectionPK(String studentID, String courseID) {
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

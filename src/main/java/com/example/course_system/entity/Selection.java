package com.example.course_system.entity;

import javax.persistence.*;

@Entity
@Table(name = "selection")
@IdClass(SelectionPK.class)
public class Selection {
    @Id
    @Column(name = "student_id")
    private String studentID;

    @Id
    @Column(name = "course_id")
    private String courseID;


//    Constructor
    public Selection() {}
    public Selection(String studentID, String courseID) {
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
package com.example.course_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentID;
    @Column(name = "student_name")
    private String studentName;

//    Constructor
    public Student() {}
    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }


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

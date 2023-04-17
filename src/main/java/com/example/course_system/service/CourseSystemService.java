package com.example.course_system.service;

import com.example.course_system.entity.Course;
import com.example.course_system.entity.Selection;
import com.example.course_system.vo.*;

import java.util.List;

public interface CourseSystemService {
//    Course Service
    CourseResponse addCourse(CourseRequest courseRequest);
    CourseResponse setCourse(CourseRequest courseRequest);
    CourseResponse deleteCourse(CourseRequest courseRequest);
    List<Course> getAllCourse();


//    Student Service
    StudentResponse addStudent(StudentRequest studentRequest);
    StudentResponse setStudent(StudentRequest studentRequest);
    StudentResponse deleteStudent(StudentRequest studentRequest);


//    Selection Service
    SelectionResponse addSelection(SelectionRequest selectionRequest);
    SelectionResponse deleteSelection(SelectionRequest selectionRequest);
    SelectionResponse getSelectionByStudent(SelectionRequest selectionRequest);
}

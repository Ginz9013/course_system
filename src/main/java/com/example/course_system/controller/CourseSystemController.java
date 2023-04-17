package com.example.course_system.controller;

import com.example.course_system.entity.Course;
import com.example.course_system.entity.Selection;
import com.example.course_system.service.CourseSystemService;
import com.example.course_system.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CourseSystemController {

    @Autowired
    private CourseSystemService courseSystemService;

//    ---- COURSE SERVICE ----
    @PostMapping("/add_course")
    public CourseResponse addCourse(@RequestBody CourseRequest courseRequest){
        return courseSystemService.addCourse(courseRequest);
    }

    @PostMapping("/set_course")
    public CourseResponse setCourse(@RequestBody CourseRequest courseRequest) {
        return courseSystemService.setCourse(courseRequest);
    }

    @PostMapping(" ")
    public CourseResponse deleteCourse(@RequestBody CourseRequest courseRequest) {
        return courseSystemService.deleteCourse(courseRequest);
    }

    @GetMapping("/get_all_course")
    public List<Course> getAllCourse() {
        return courseSystemService.getAllCourse();
    }



//    ---- STUDENT SERVICE ----
    @PostMapping("/add_student")
    public StudentResponse addStudent(@RequestBody StudentRequest studentRequest) {
        return courseSystemService.addStudent(studentRequest);
    }

    @PostMapping("/set_student")
    public StudentResponse setStudent(@RequestBody StudentRequest studentRequest) {
        return courseSystemService.setStudent(studentRequest);
    }

    @PostMapping("/delete_student")
    public StudentResponse deleteStudent(@RequestBody StudentRequest studentRequest) {
        return courseSystemService.deleteStudent(studentRequest);
    }



//    ---- SELECTION SERVICE ----
    @PostMapping("/add_selection")
    public SelectionResponse addSelection(@RequestBody SelectionRequest selectionRequest) {
        return courseSystemService.addSelection(selectionRequest);
    }

    @PostMapping("/delete_selection")
    public SelectionResponse deleteSelection(@RequestBody SelectionRequest selectionRequest) {
        return courseSystemService.deleteSelection(selectionRequest);
    }

    @PostMapping("/get_selection_by_student")
    public SelectionResponse getSelectionByStudent(@RequestBody SelectionRequest selectionRequest) {
        return courseSystemService.getSelectionByStudent(selectionRequest);
    }
}

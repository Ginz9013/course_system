package com.example.course_system.repository;

import com.example.course_system.entity.Selection;
import com.example.course_system.entity.SelectionPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SelectionDao extends JpaRepository<Selection, SelectionPK> {
    List<Selection> findByStudentID(String studentID);
    List<Selection> findByCourseID(String courseID);
}

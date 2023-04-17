package com.example.course_system;

import com.example.course_system.service.CourseSystemService;
import com.example.course_system.vo.SelectionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseSystemApplicationTests {
	@Autowired
	CourseSystemService courseSystemService;
	@Test
	void contextLoads() {

		courseSystemService.getSelectionByStudent(new SelectionRequest("S002"));
	}

}

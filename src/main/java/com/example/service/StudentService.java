package com.example.service;

import java.util.List;

import com.example.model.Course;
import com.example.model.Student;

public interface StudentService {
	
	Student register(Student student);
	
	Student getById(int studentId);
	
	List<Student> getAllStudents();
	
	Course enrollForACourse(int studentId, int courseId);
	
	List<Course> getEnrolledCoursesForAStudent(int studentId);

}
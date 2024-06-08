package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.exceptions.ExaminationSessionAlreadyCreated;
import com.example.exceptions.NoSuchCourseFoundException;
import com.example.model.Course;
import com.example.model.ExaminationSession;
import com.example.model.dto.ExaminationSessionDto;
import com.example.repository.CourseRepository;
import com.example.repository.ExaminationSessionRepository;

@Service
public class ExaminationSessionServiceImpl implements ExaminationSessionService {
	
	private CourseRepository courseRepository;
	
	private ExaminationSessionRepository examinationSessionRepository;
	
	

	public ExaminationSessionServiceImpl(CourseRepository courseRepository, ExaminationSessionRepository examinationSessionRepository) {
		super();
		this.courseRepository = courseRepository;
		this.examinationSessionRepository = examinationSessionRepository;
	}

	@Override
	public ExaminationSession createExamSessionForACourse(ExaminationSessionDto dto, int courseId) {
		
		Course course = checkIfCourseExists(courseId);
		
		// check if no current exam session
		
		ExaminationSession currentSession = examinationSessionRepository.findCurrentExaminationSession(courseId);
		
		if (currentSession == null) {
			currentSession = new ExaminationSession();
			currentSession.setCourse(course);
			currentSession.setSessionClosed(false);
			currentSession.setStartTime(dto.getStartTime());
			currentSession.setEndTime(dto.getEndTime());
			
			return examinationSessionRepository.save(currentSession);
		}
		else {
			throw new ExaminationSessionAlreadyCreated(courseId);
		}
		
	}

	private Course checkIfCourseExists(int courseId) {
		Optional<Course> optional = courseRepository.findById(courseId);
		
		if (optional.isEmpty()) {
			throw new NoSuchCourseFoundException(courseId);
		}
		
		Course course = optional.get();
		
		return course;
	}

	@Override
	public ExaminationSession closeExamSessionForACourse(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

}

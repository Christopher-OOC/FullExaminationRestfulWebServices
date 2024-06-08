package com.example.exceptions;

public class NoQuestionAvailableForTheCourseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoQuestionAvailableForTheCourseException(int courseId) {
		super("No question available for the course with ID: " + courseId);
	}

}

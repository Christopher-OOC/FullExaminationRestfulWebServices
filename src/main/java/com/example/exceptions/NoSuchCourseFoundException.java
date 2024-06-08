package com.example.exceptions;

public class NoSuchCourseFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchCourseFoundException(int courseId) {
		super("No course found with courseId: " + courseId);
	}
}

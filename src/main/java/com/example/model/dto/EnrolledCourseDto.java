package com.example.model.dto;

import java.util.Date;
import java.util.Objects;

import com.example.model.entity.EnrolledCourseId;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnrolledCourseDto {

	private EnrolledCourseId enrolledCourseId = new EnrolledCourseId();

	private StudentDto student;

	private CourseDto course;

	private Date enrolledOn;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnrolledCourseDto other = (EnrolledCourseDto) obj;
		return Objects.equals(enrolledCourseId, other.enrolledCourseId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(enrolledCourseId);
	}
}

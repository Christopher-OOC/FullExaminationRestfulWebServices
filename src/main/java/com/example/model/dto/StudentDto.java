package com.example.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.example.model.entity.EnrolledCourse;
import com.example.model.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {
	
	private int id;
	
	private String studentId;
	
	private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String email;
	
	private String password;
	
	private String pin;
	
	private Date dateOfBirth;
	
	private List<EnrolledCourse> coursesTaken = new ArrayList<>();
	
	private User user;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		return studentId == other.studentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId);
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", studentId=" + studentId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", email=" + email + ", password=" + password
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
	
}

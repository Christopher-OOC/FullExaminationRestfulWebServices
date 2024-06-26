package com.example.model.dto;

import java.util.Objects;

import com.example.model.entity.Question;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionOptionDto {

	private long optionId;

	private Question question;

	private String optionValue;

	private boolean isCorrect;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionOptionDto other = (QuestionOptionDto) obj;
		return optionId == other.optionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(optionId);
	}
}

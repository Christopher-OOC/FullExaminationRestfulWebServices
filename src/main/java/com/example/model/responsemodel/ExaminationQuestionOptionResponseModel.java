package com.example.model.responsemodel;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExaminationQuestionOptionResponseModel {
	
	private int answerOptionId;
	
	private String optionValue;
	
	private boolean isCorrect;
	
	private boolean chosen;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExaminationQuestionOptionResponseModel other = (ExaminationQuestionOptionResponseModel) obj;
		return answerOptionId == other.answerOptionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answerOptionId);
	}

}

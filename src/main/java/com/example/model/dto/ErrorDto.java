package com.example.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDto {
	
	@JsonProperty("error_date")
	private Date errorDate = new Date();
	
	private int status;
	
	private String path;
	
	private List<String> messages = new ArrayList<>();

}

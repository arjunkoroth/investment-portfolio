package com.hackathon.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class FieldErrorDto {
	private int errorCode;
	private String errorMessage;
	private List<String> errors;

}

package com.example.exception.handler;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.ExaminationSessionAlreadyCreated;
import com.example.exceptions.NoCourseAvailableException;
import com.example.exceptions.NoExaminationSessionException;
import com.example.exceptions.NoOngoingExaminationException;
import com.example.exceptions.NoStudentAvailableException;
import com.example.exceptions.NoSuchCourseFoundException;
import com.example.exceptions.NoSuchInstructorException;
import com.example.exceptions.NoSuchQuestionFoundException;
import com.example.exceptions.NoSuchStudentFoundException;
import com.example.exceptions.NotRegisteredForTheCourseException;
import com.example.model.dto.ErrorDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoSuchStudentFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoSuchStudentFoundException(HttpServletRequest request, NoSuchStudentFoundException ex) {

		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());
		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NoSuchInstructorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoSuchInstructorException(HttpServletRequest request, NoSuchInstructorException ex) {

		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());
		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NoStudentAvailableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoStudentAvailableException(HttpServletRequest request, NoStudentAvailableException ex) {

		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NoCourseAvailableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoCourseAvailableException(HttpServletRequest request, NoCourseAvailableException ex) {

		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NoSuchCourseFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoSuchCourseFoundException(HttpServletRequest request, NoSuchCourseFoundException ex) {

		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NoSuchQuestionFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoSuchCourseFoundException(HttpServletRequest request, NoSuchQuestionFoundException ex) {
		
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NoExaminationSessionException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoExaminationSessionException(HttpServletRequest request, NoExaminationSessionException ex) {
		
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NoOngoingExaminationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNoOngoingExaminationException(HttpServletRequest request, NoOngoingExaminationException ex) {
		
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException ex) {
		
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(request.getServletPath());		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	@ExceptionHandler(NotRegisteredForTheCourseException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDto handleNotRegisteredForTheCourseException(HttpServletRequest request, NotRegisteredForTheCourseException ex) {
		
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setPath(request.getServletPath());		
		error.getMessages().add(ex.getMessage());
		
		return error;
		
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDto handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {

		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(request.getServletPath());
		
		ex.getConstraintViolations().forEach(violation -> {
			error.getMessages().add(violation.getMessage());
		});
		
		
		return error;
	}
	
	@ExceptionHandler(ExaminationSessionAlreadyCreated.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDto handleConstraintViolationException(HttpServletRequest request, ExaminationSessionAlreadyCreated ex) {

		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(request.getServletPath());
		error.getMessages().add(ex.getMessage());

		return error;
	}
	
	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(((ServletWebRequest)request).getRequest().getServletPath());
		
		List<FieldError> fieldErrors = ex.getFieldErrors();
		
		fieldErrors.forEach(fieldError -> {
			error.getMessages().add(fieldError.getDefaultMessage());
		});
		
		return new ResponseEntity<>(error, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(((ServletWebRequest)request).getRequest().getServletPath());
		
		error.getMessages().add("The route is not valid");
		
		return new ResponseEntity<>(error, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		ErrorDto error = new ErrorDto();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(((ServletWebRequest)request).getRequest().getServletPath());
		
		error.getMessages().add("There must be information provided in the request body");
		
		return new ResponseEntity<>(error, headers, status);
	}
	
	

}
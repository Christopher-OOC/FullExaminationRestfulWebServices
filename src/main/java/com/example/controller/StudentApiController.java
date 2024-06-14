package com.example.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.BadRequestException;
import com.example.model.dto.CourseDto;
import com.example.model.dto.StudentDto;
import com.example.model.entity.Course;
import com.example.model.requestmodel.StudentRequestModel;
import com.example.model.responsemodel.RequestStatusType;
import com.example.model.responsemodel.ResponseMessageModel;
import com.example.model.responsemodel.ResponseStatusType;
import com.example.model.responsemodel.StudentResponseModel;
import com.example.service.StudentService;
import com.example.utilities.PublicIdGeneratorUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/students")
public class StudentApiController {

	private StudentService studentService;

	private ModelMapper modelMapper;

	private Map<String, String> propertyMap = Map.of("first_name", "firstName", "last_name", "lastName", "middle_name",
			"middleName");

	public StudentApiController(StudentService studentService, ModelMapper modelMapper) {
		super();
		this.studentService = studentService;
		this.modelMapper = modelMapper;

	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody @Valid StudentRequestModel requestModel) {
		StudentDto dto = modelMapper.map(requestModel, StudentDto.class);

		dto.setStudentId(PublicIdGeneratorUtils.generateId(30));

		studentService.register(dto);

		ResponseMessageModel message = new ResponseMessageModel();
		message.setResponseStatusType(ResponseStatusType.SUCCESS);
		message.setRequestStatusType(RequestStatusType.CREATED);

		return ResponseEntity.created(null).body(message);
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<?> getStudentById(@PathVariable("studentId") String studentId) {
		StudentDto dto = studentService.getByStudentId(studentId);

		StudentResponseModel response = modelMapper.map(dto, StudentResponseModel.class);

		addSelfLinks(List.of(response));

		return ResponseEntity.ok(response);
	}

	private void addSelfLinks(List<StudentResponseModel> listResponse) {
		listResponse.forEach(response -> {
			response.add(linkTo(methodOn(getClass()).getStudentById(response.getStudentId())).withSelfRel());
		});
	}

	@GetMapping
	public ResponseEntity<?> getAllStudent(
			@RequestParam(value = "page", required = false, defaultValue = "1") @Min(value = 1, message = "Page number must not be less than 1") int pageNum,
			@RequestParam(value = "size", required = false, defaultValue = "3") @Min(value = 1, message = "Page size must not be less than 1") @Max(value = 10, message = "Page size must not be greather than 10") int pageSize,
			@RequestParam(value = "sort", required = false, defaultValue = "last_name,first_name,middle_name") String sortOptions,
			@RequestParam(value = "search", required = false, defaultValue = "") String search) {

		String[] arrSortFields = sortOptions.split(",");

		for (String sortField : arrSortFields) {
			String actualField = sortField.replace("-", "");

			if (!propertyMap.keySet().contains(actualField)) {
				throw new BadRequestException("Invalid sort field: " + actualField);
			}
		}

		Page<StudentDto> studentPage = studentService.getAllStudents(pageNum - 1, pageSize, sortOptions, search);

		List<StudentDto> listDto = studentPage.getContent();
		
		log.info("List Student: {}", listDto);
		
		java.lang.reflect.Type typeToken = new TypeToken<List<StudentResponseModel>>() {}.getType();
		List<StudentResponseModel> listResponse = modelMapper.map(listDto, typeToken);

		addSelfLinks(listResponse);
		
		long totalElements = studentPage.getTotalElements();
		long totalPages = studentPage.getTotalPages();
		
		PageMetadata metadata = new PageMetadata(pageSize, pageNum, totalElements, totalPages);
		
		PagedModel<StudentResponseModel> pagedModel = PagedModel.of(listResponse, metadata);
		
		return ResponseEntity.ok(pagedModel);
	}

	@PostMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<?> enrollForACourse(@PathVariable("studentId") String studentId,
			@PathVariable("courseId") String courseId) {
		Course enrolledCourse = studentService.enrollForACourse(studentId, courseId);

		URI uri = URI.create("/v1/students/" + studentId + "/courses/" + courseId);

		return ResponseEntity.created(uri).body(modelMapper.map(enrolledCourse, CourseDto.class));
	}

	@GetMapping("/{studentId}/courses")
	public ResponseEntity<?> getAllCourseEnrolledByStudent(@PathVariable("studentId") String studentId) {
		List<CourseDto> allEnrolledCourses = studentService.getEnrolledCoursesForAStudent(studentId);

		return ResponseEntity.ok(allEnrolledCourses);
	}
}

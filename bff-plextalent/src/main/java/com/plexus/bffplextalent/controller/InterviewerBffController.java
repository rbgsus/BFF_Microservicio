package com.plexus.bffplextalent.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bff/api/v1/interviewers")
@RequiredArgsConstructor
public class InterviewerBffController {

	private final RestTemplate restTemplate;

	@PostMapping("/interviewers")
	@ApiOperation(value = "Save an HR interviewer", notes = "Save an HR interviewer to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "HR interviewer saved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveInterviewer(@RequestBody Object interviewer) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/interviewers/interviewers", HttpMethod.POST,
					new HttpEntity<>(interviewer), Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/interviewers")
	@ApiOperation(value = "Get all HR interviewers", notes = "Get a list of all HR interviewer objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllInterviewers() {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/interviewers/interviewers", HttpMethod.GET, null, Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/interviewers/{id}")
	@ApiOperation(value = "Get HR interviewer by ID", notes = "Get an HR interviewer object from the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getInterviewerById(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/interviewers/interviewers/{id}", HttpMethod.GET, null, Object.class,
					id);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@PutMapping("/interviewers/{id}")
	@ApiOperation(value = "Update HR interviewer", notes = "Update an HR interviewer in the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "HR interviewer updated successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateInterviewer(@PathVariable(name = "id") Long id, @RequestBody Object interviewer) {
		try {
			ResponseEntity<Object> updatedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/interviewers/interviewers/{id}", HttpMethod.PUT,
					new HttpEntity<>(interviewer), Object.class, id);

			return ResponseEntity.status(updatedResponse.getStatusCode()).body(updatedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@DeleteMapping("/interviewers/{id}")
	@ApiOperation(value = "Delete HR interviewer", notes = "Delete an HR interviewer from the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "HR interviewer deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteInterviewer(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> deletedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/interviewers/interviewers/{id}", HttpMethod.DELETE, null,
					Object.class, id);

			return ResponseEntity.status(deletedResponse.getStatusCode()).body(deletedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/interviewerPage")
	@Operation(summary = "Get paginated HR interviewer", description = "Get a paginated list of HR interviewers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getPaginatedInterviewers(
			@Parameter(in = ParameterIn.QUERY, name = "page", description = "Page number", required = true) @RequestParam(name = "page") int page,
			@Parameter(in = ParameterIn.QUERY, name = "size", description = "Number of items per page", required = true) @RequestParam(name = "size") int size) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/interviewers/interviewerPage?page={page}&size={size}", HttpMethod.GET,
					null, Object.class, page, size);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}
}

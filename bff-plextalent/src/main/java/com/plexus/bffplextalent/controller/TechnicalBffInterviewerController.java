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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bff/api/v1/technical_interviewers")
@RequiredArgsConstructor
public class TechnicalBffInterviewerController {

	private final RestTemplate restTemplate;

	@PostMapping("/technical_interviewers")
	@Operation(summary = "Save Technical Interviewer", description = "Save a TechnicalInterviewer object to the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Technical Interviewer saved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveTechnicalInterviewer(@RequestBody Object technicalInterviewer) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_interviewers/technical_interviewers", HttpMethod.POST,
					new HttpEntity<>(technicalInterviewer), Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/technical_interviewers")
	@Operation(summary = "Get all Technical Interviewers", description = "Get a list of all TechnicalInterviewerDTO objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllTechnicalInterviewers() {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_interviewers/technical_interviewers", HttpMethod.GET, null,
					Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/technical_interviewers/{id}")
	@Operation(summary = "Get Technical Interviewer by ID", description = "Get a TechnicalInterviewerDTO object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getTechnicalInterviewerById(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_interviewers/technical_interviewers/{id}", HttpMethod.GET,
					null, Object.class, id);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@PutMapping("/technical_interviewers/{id}")
	@Operation(summary = "Update Technical Interviewer", description = "Update a TechnicalInterviewer object in the database based on the provided ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Technical Interviewer updated successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateTechnicalInterviewer(@PathVariable(name = "id") Long id,
			@RequestBody Object technicalInterviewer) {
		try {
			ResponseEntity<Object> updatedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_interviewers/technical_interviewers/{id}", HttpMethod.PUT,
					new HttpEntity<>(technicalInterviewer), Object.class, id);

			return ResponseEntity.status(updatedResponse.getStatusCode()).body(updatedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@DeleteMapping("/technical_interviewers/{id}")
	@Operation(summary = "Delete Technical Interviewer", description = "Delete a TechnicalInterviewer object from the database based on the provided ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Technical Interviewer deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewer not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteTechnicalInterviewer(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> deletedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_interviewers/technical_interviewers/{id}",
					HttpMethod.DELETE, null, Object.class, id);

			return ResponseEntity.status(deletedResponse.getStatusCode()).body(deletedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/technical_interviewersPage")
	@Operation(summary = "Get paginated Technical Interviewer", description = "Get a paginated list of TechnicalInterviewers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Interviewers not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getPaginatedTechnicalInterviewers(@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_interviewers/technical_interviewersPage?page={page}&size={size}",
					HttpMethod.GET, null, Object.class, page, size);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}
}

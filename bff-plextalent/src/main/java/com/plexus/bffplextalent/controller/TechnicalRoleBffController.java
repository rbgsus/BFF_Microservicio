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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/bff/api/v1/technical_roles")
@RequiredArgsConstructor
public class TechnicalRoleBffController {

	private final RestTemplate restTemplate;

	@PostMapping("/technical_roles")
	@Operation(summary = "Save Technical Role", description = "Save a TechnicalRole object to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successful operation, created"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Role"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveTechnicalRole(@RequestBody Object technicalRole) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_roles/technical_roles", HttpMethod.POST,
					new HttpEntity<>(technicalRole), Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/technical_roles")
	@Operation(summary = "Get all Technical Roles", description = "Get a list of all TechnicalRole objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Roles not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllTechnicalRoles() {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_roles/technical_roles", HttpMethod.GET, null, Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/technical_roles/{id}")
	@Operation(summary = "Get Technical Role by ID", description = "Get a TechnicalRole object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technical Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getTechnicalRoleById(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_roles/technical_roles/{id}", HttpMethod.GET, null,
					Object.class, id);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@PutMapping("/technical_roles/{id}")
	@Operation(summary = "Update Technical Role by ID", description = "Update a TechnicalRole object in the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Role"),
			@ApiResponse(responseCode = "404", description = "Technical Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateTechnicalRole(@PathVariable(name = "id") Long id,
			@RequestBody Object technicalRole) {
		try {
			ResponseEntity<Object> updatedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_roles/technical_roles/{id}", HttpMethod.PUT,
					new HttpEntity<>(technicalRole), Object.class, id);

			return ResponseEntity.status(updatedResponse.getStatusCode()).body(updatedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@DeleteMapping("/technical_roles/{id}")
	@Operation(summary = "Delete Technical Role by ID", description = "Delete a TechnicalRole object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Successful operation, no content"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid Technical Role"),
			@ApiResponse(responseCode = "404", description = "Technical Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteTechnicalRole(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> deletedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/technical_roles/technical_roles/{id}", HttpMethod.DELETE, null,
					Object.class, id);

			return ResponseEntity.status(deletedResponse.getStatusCode()).body(deletedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}
}

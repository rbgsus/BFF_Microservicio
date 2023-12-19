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
@RequestMapping("/bff/api/v1/technologies")
@RequiredArgsConstructor
public class TechnologyBffController {

	private final RestTemplate restTemplate;

	@PostMapping("/technologies")
	@Operation(summary = "Save Technology", description = "Save a Technology object to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Technology saved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveTechnology(@RequestBody Object technology) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technologies/technologies", HttpMethod.POST,
					new HttpEntity<>(technology), Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/technologies")
	@Operation(summary = "Get all Technologies", description = "Get a list of all Technology objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technologies not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllTechnologies() {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technologies/technologies", HttpMethod.GET, null, Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/technologies/{id}")
	@Operation(summary = "Get Technology by ID", description = "Get a Technology object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Technology not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getTechnologyById(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/technologies/technologies/{id}", HttpMethod.GET, null, Object.class,
					id);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@PutMapping("/technologies/{id}")
	@Operation(summary = "Update Technology by ID", description = "Update a Technology object in the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "Technology not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateTechnology(@PathVariable(name = "id") Long id, @RequestBody Object technology) {
		try {
			ResponseEntity<Object> updatedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/technologies/technologies/{id}", HttpMethod.PUT,
					new HttpEntity<>(technology), Object.class, id);

			return ResponseEntity.status(updatedResponse.getStatusCode()).body(updatedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@DeleteMapping("/technologies/{id}")
	@Operation(summary = "Delete Technology by ID", description = "Delete a Technology object from the database based on the provided ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Successful operation, no content"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "Technology not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteTechnology(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> deletedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/technologies/technologies/{id}", HttpMethod.DELETE, null,
					Object.class, id);

			return ResponseEntity.status(deletedResponse.getStatusCode()).body(deletedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}
}

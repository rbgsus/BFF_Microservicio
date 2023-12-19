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
@RequestMapping("/bff/api/v1/companies")
@RequiredArgsConstructor
public class CompanyBffController {

	private final RestTemplate restTemplate;

	@PostMapping("/companies")
	@Operation(summary = "Save an HR provider company", description = "Save an HR provider company to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "HR provider company saved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> saveCompany(@RequestBody Object company) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange("http://localhost:8080/api/v1/companies/companies",
					HttpMethod.POST, new HttpEntity<>(company), Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/companies")
	@Operation(summary = "Get all HR provider companies", description = "Get a list of all HR provider company objects from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR provider companies not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getAllCompanies() {
		try {
			ResponseEntity<Object> response = restTemplate.exchange("http://localhost:8080/api/v1/companies/companies",
					HttpMethod.GET, null, Object.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@GetMapping("/companies/{id}")
	@Operation(summary = "Get HR provider company by ID", description = "Get an HR provider company object from the database based on the given ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "HR provider company not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> getCompanyById(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> response = restTemplate.exchange(
					"http://localhost:8080/api/v1/companies/companies/{id}", HttpMethod.GET, null, Object.class, id);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@PutMapping("/companies/{id}")
	@Operation(summary = "Update HR provider company", description = "Update an HR provider company in the database based on the given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "HR provider company updated successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR provider company not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> updateCompany(@PathVariable(name = "id") Long id, @RequestBody Object company) {
		try {
			ResponseEntity<Object> updatedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/companies/companies/{id}", HttpMethod.PUT, new HttpEntity<>(company),
					Object.class, id);

			return ResponseEntity.status(updatedResponse.getStatusCode()).body(updatedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}

	@DeleteMapping("/companies/{id}")
	@Operation(summary = "Delete HR provider company", description = "Delete an HR provider company from the database based on the given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "HR provider company deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "HR provider company not found"),
			@ApiResponse(responseCode = "500", description = "Internal error") })
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Long id) {
		try {
			ResponseEntity<Object> deletedResponse = restTemplate.exchange(
					"http://localhost:8080/api/v1/companies/companies/{id}", HttpMethod.DELETE, null, Object.class, id);

			return ResponseEntity.status(deletedResponse.getStatusCode()).body(deletedResponse.getBody());

		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
		}
	}
}

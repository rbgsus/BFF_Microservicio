package com.plexus.bffplextalent.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.plexus.bffplextalent.service.BffService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BffServiceImpl implements BffService {

	private final RestTemplate restTemplate;

	/**
	 * Gets a resource from the backend using RestTemplate.
	 *
	 * @return The resource retrieved from the backend.
	 */

	public String getBackendResource() {
		String backendUrl = "http://localhost:8080/api/v1";
		return restTemplate.getForObject(backendUrl, String.class);
	}

}

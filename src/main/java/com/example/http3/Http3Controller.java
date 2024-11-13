package com.example.http3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
class Http3Controller {

	private final WebClient http3WebClient;

	Http3Controller(WebClient http3WebClient) {
		this.http3WebClient = http3WebClient;
	}

	@GetMapping("/hello")
	String hello() {
		return "Hello HTTP/3!";
	}

	@GetMapping("/remote")
	Mono<String> remote() {
		return http3WebClient.get()
				.uri("https://projectreactor.io/")
				.retrieve()
				.bodyToMono(String.class);
	}
}

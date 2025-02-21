package me.shortenurl.ui;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.shortenurl.application.SimpleShortenUrlService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shorten-urls")
public class ShortenUrlController {
	private final SimpleShortenUrlService shortenUrlService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ShortenUrlResponseDto createShortenUrl(@Valid @RequestBody ShortenUrlRequestDto request) {
		return shortenUrlService.generateShortenUrl(request);
	}

	@ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
	@GetMapping("/redirect/{shortenUrlKey}")
	public HttpHeaders redirectToShortenUrl(@PathVariable String shortenUrlKey) throws URISyntaxException {
		String originalUrl = shortenUrlService.getOriginalUrlByShortenKey(shortenUrlKey);

		URI redirectUri = new URI(originalUrl);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUri);

		return httpHeaders;
	}

	@GetMapping("/{shortenUrlKey}")
	public ShortenUrlInformationDto getShortenUrl(@PathVariable String shortenUrlKey) {
		return shortenUrlService.getShortenUrlInformationByShortenKey(shortenUrlKey);
	}

}

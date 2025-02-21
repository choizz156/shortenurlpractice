package me.shortenurl.ui;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.shortenurl.domain.ShortenUrl;

@Data
public class ShortenUrlResponseDto {
	private String originalUrl;
	private String shortenedUrlKey;

	public ShortenUrlResponseDto(ShortenUrl shortenUrl) {
		this.originalUrl = shortenUrl.getOriginalUrl();
		this.shortenedUrlKey = shortenUrl.getShortenedUrl();
	}
}

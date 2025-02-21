package me.shortenurl.ui;

import lombok.Data;
import me.shortenurl.domain.ShortenUrl;

@Data
public class ShortenUrlInformationDto {
	private String originalUrl;
	private String shortenedUrlKey;
	private Long redirectCount;

	public ShortenUrlInformationDto(ShortenUrl shortenUrl) {
		this.originalUrl = shortenUrl.getOriginalUrl();
		this.shortenedUrlKey = shortenUrl.getShortenedUrl();
		this.redirectCount = shortenUrl.getRedirectCount();
	}
}

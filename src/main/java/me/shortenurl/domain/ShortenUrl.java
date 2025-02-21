package me.shortenurl.domain;

import java.util.Random;

import lombok.Getter;

@Getter
public class ShortenUrl {

	private String originalUrl;
	private String shortenedUrl;
	private Long redirectCount;

	public ShortenUrl(String originalUrl, String shortenedUrl) {
		this.originalUrl = originalUrl;
		this.shortenedUrl = shortenedUrl;
		redirectCount = 0L;
	}

	public static String toShortenUrlKey() {

		String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVMXYZabcdefghijkmnpqrs";
		Random random = new Random();

		StringBuilder shortenedUrlBuilder = new StringBuilder();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(0, base56Characters.length());
			char base56Character = base56Characters.charAt(index);
			shortenedUrlBuilder.append(base56Character);
		}

		return shortenedUrlBuilder.toString();
	}

	public void increaseCount() {
		redirectCount++;
	}
}

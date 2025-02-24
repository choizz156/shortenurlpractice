package me.shortenurl.domain;

public class LackOfShortenUrlKeyException extends RuntimeException {

	public LackOfShortenUrlKeyException(String message) {
		super(message);
	}
}

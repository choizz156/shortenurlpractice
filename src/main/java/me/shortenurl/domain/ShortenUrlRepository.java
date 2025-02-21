package me.shortenurl.domain;

import org.springframework.stereotype.Repository;


public interface ShortenUrlRepository {
	void save(ShortenUrl shortenUrl);
	ShortenUrl findByKey(String shortenUrlKey);
}

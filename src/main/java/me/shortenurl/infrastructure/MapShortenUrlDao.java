package me.shortenurl.infrastructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import me.shortenurl.domain.ShortenUrl;
import me.shortenurl.domain.ShortenUrlRepository;

@Repository
public class MapShortenUrlDao implements ShortenUrlRepository {

	private final Map<String, ShortenUrl> shortenUrls = new ConcurrentHashMap<>();

	@Override
	public void save(ShortenUrl shortenUrl) {
		shortenUrls.put(shortenUrl.getShortenedUrl(), shortenUrl);
	}

	@Override
	public ShortenUrl findByKey(String shortenUrlKey) {
		return shortenUrls.get(shortenUrlKey);
	}
}

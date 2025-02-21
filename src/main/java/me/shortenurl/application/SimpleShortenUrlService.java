package me.shortenurl.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.shortenurl.domain.ShortenUrl;
import me.shortenurl.domain.ShortenUrlRepository;
import me.shortenurl.ui.ShortenUrlController;
import me.shortenurl.ui.ShortenUrlInformationDto;
import me.shortenurl.ui.ShortenUrlRequestDto;
import me.shortenurl.ui.ShortenUrlResponseDto;

@RequiredArgsConstructor
@Service
public class SimpleShortenUrlService {

	private final ShortenUrlRepository shortenUrlRepository;

	public ShortenUrlResponseDto generateShortenUrl(ShortenUrlRequestDto requestDto) {
		String originalUrl = requestDto.getOriginalUrl();
		String shortenUrlKey = ShortenUrl.toShortenUrlKey();

		ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);
		shortenUrlRepository.save(shortenUrl);

		return new ShortenUrlResponseDto(shortenUrl);
	}

	public ShortenUrlInformationDto getShortenUrlInformationByShortenKey(String shortenUrlKey) {
		ShortenUrl shortenUrl = shortenUrlRepository.findByKey(shortenUrlKey);
		return new ShortenUrlInformationDto(shortenUrl);
	}

	public String getOriginalUrlByShortenKey(String shortenUrlKey) {
		ShortenUrl shortenUrl = shortenUrlRepository.findByKey(shortenUrlKey);
		shortenUrl.increaseCount();
		return shortenUrl.getOriginalUrl();
	}
}

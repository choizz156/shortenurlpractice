package me.shortenurl.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shortenurl.domain.LackOfShortenUrlKeyException;
import me.shortenurl.domain.NotFoundShortenUrlException;
import me.shortenurl.domain.ShortenUrl;
import me.shortenurl.domain.ShortenUrlRepository;
import me.shortenurl.ui.ShortenUrlInformationDto;
import me.shortenurl.ui.ShortenUrlRequestDto;
import me.shortenurl.ui.ShortenUrlResponseDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class SimpleShortenUrlService {

	private final ShortenUrlRepository shortenUrlRepository;

	public ShortenUrlResponseDto generateShortenUrl(ShortenUrlRequestDto requestDto) {
		String originalUrl = requestDto.getOriginalUrl();
		String shortenUrlKey = getUniqueShortenUrlKey();

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

		if (shortenUrl == null) {
			throw new NotFoundShortenUrlException("Shorten url not found");
		}

		shortenUrl.increaseCount();
		return shortenUrl.getOriginalUrl();
	}

	private String getUniqueShortenUrlKey() {
		final int MAX_RETRY_COUNT = 3;
		int retryCount = 0;

		while(retryCount++ < MAX_RETRY_COUNT){
			String shortenUrlKey = ShortenUrl.toShortenUrlKey();
			ShortenUrl shortenUrl = shortenUrlRepository.findByKey(shortenUrlKey);
			if(shortenUrl == null){
				return shortenUrlKey;
			}

			log.warn("단축 url 재시도 횟수 : {}", retryCount + 1);

		}

		throw new LackOfShortenUrlKeyException("url 키가 부족합니다.");
	}
}

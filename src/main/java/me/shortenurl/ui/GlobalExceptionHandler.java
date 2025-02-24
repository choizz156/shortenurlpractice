package me.shortenurl.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import me.shortenurl.domain.LackOfShortenUrlKeyException;
import me.shortenurl.domain.NotFoundShortenUrlException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(LackOfShortenUrlKeyException.class)
	public ResponseEntity<String> handleLackOfShortenUrlKeyException(LackOfShortenUrlKeyException e) {
		log.error("단축 url 자원 부족");
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundShortenUrlException.class)
	public ResponseEntity<String> handleNotFoundShortenUrlException(
		NotFoundShortenUrlException ex
	) {
		log.info(ex.getMessage());
		return new ResponseEntity<>("단축 URL을 찾지 못했습니다.", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException ex
	) {

		StringBuilder errorMessage = new StringBuilder("유효성 검증 실패: ");
		ex.getBindingResult()
			.getFieldErrors()
			.forEach(error -> {
				errorMessage.append(String.format("필드 '%s': %s. ", error.getField(), error.getDefaultMessage()));
			});


		log.debug("잘못된 요청: {}", errorMessage);


		return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	}
}

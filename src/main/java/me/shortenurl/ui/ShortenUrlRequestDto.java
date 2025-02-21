package me.shortenurl.ui;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShortenUrlRequestDto {

	@NotNull
	@URL(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)" )
	private String originalUrl;
}

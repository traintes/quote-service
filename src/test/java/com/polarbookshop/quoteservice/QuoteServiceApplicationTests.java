package com.polarbookshop.quoteservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.polarbookshop.quoteservice.domain.Genre;
import com.polarbookshop.quoteservice.domain.Quote;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuoteServiceApplicationTests {
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void whenAllQuotesThenReturn() {
		this.webTestClient
			.get()
			.uri("/quotes")
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBodyList(Quote.class);
	}
	
	@Test
	void whenRandomQuoteThenReturn() {
		this.webTestClient
			.get()
			.uri("/quotes/random")
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody(Quote.class);
	}
	
	@Test
	void whenRandomQuoteByGenreThenReturn() {
		this.webTestClient
			.get()
			.uri("/quotes/random/FANTASY")
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody(Quote.class)
			.value(quote -> assertThat(quote.genre()).isEqualTo(Genre.FANTASY));
	}
}

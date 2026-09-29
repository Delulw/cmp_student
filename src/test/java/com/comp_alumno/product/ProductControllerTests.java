package com.comp_alumno.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void supportsProductCrudLifecycle() {
		ProductRequest request = new ProductRequest("Notebook", "A5 notebook", new BigDecimal("12.50"));

		Product created = webTestClient.post()
				.uri("/api/products")
				.bodyValue(request)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().exists("Location")
				.expectBody(Product.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(created);
		assertNotNull(created.id());
		assertEquals(request.name(), created.name());

		webTestClient.get()
				.uri("/api/products/{id}", created.id())
				.exchange()
				.expectStatus().isOk()
				.expectBody(Product.class)
				.value(product -> assertEquals(created.id(), product.id()));

		webTestClient.get()
				.uri("/api/products")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Product.class)
				.value(products -> assertEquals(1, products.size()));

		ProductRequest updatedRequest = new ProductRequest("Notebook Pro", "Updated notebook", new BigDecimal("15.00"));
		webTestClient.put()
				.uri("/api/products/{id}", created.id())
				.bodyValue(updatedRequest)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Product.class)
				.value(product -> assertEquals(updatedRequest.name(), product.name()));

		webTestClient.delete()
				.uri("/api/products/{id}", created.id())
				.exchange()
				.expectStatus().isNoContent();

		webTestClient.get()
				.uri("/api/products/{id}", created.id())
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void returnsNotFoundForUnknownProduct() {
		webTestClient.get()
				.uri("/api/products/{id}", Long.MAX_VALUE)
				.exchange()
				.expectStatus().isNotFound();
	}
}